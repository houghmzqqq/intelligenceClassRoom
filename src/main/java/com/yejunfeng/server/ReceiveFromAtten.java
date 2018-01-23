package com.yejunfeng.server;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.PO.DeviceTypePO;
import com.yejunfeng.service.DeviceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.*;
import java.net.Socket;

/**
 * 处理与考勤机的交互
 */
public class ReceiveFromAtten implements Runnable{


    private ApplicationContext context;
    private DeviceService deviceService;

    private Socket client;
    private InputStream in;

    public ReceiveFromAtten(Socket client) {
        this.client = client;
    }

    public void run() {
        init();
        ByteArrayOutputStream bout = null;
//        int i = 0;
        String msg = "";
        try {
            in = client.getInputStream();
            bout = new ByteArrayOutputStream();

            byte[] buff = new byte[1024];
            int len = 0;
            while((len=in.read(buff)) != -1){
                if(len > 0){
                    msg = new String(buff,0,len);
                    System.out.println("[Receive Msg]: " + msg);
                }
                //接收来自中控设备的消息
                JSONObject jsonObject = JSONObject.fromObject(msg);
                String action = (String) jsonObject.get("op");
                if(action.equals("register")){
                    register(jsonObject);
                    System.out.println("[Register]: " + msg);
                }else if(action.equals("uploadList")){
                    uploadList(jsonObject);
                    System.out.println("[UploadList]: " + msg);
                }else if(action.equals("control")){
                    control(jsonObject);
                    System.out.println("[Control]: " + msg);
                }
                else{
                    System.out.println("[Recevice Error]: 无法识别信息，请确认\"op\"是否正确。");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收中控设备注册请求
     * @param jsonObject json数据
     * @throws IOException
     */
    public void register(JSONObject jsonObject) throws IOException {
        String controlId = (String) jsonObject.get("controlId");
        saveClient(controlId);

        OutputStream out = client.getOutputStream();
        JSONObject sendJson = new JSONObject();
        sendJson.accumulate("op","register");
        sendJson.accumulate("result","ok");
        out.write(sendJson.toString().getBytes());
    }

    /**
     * 接收中控设备发送过来的设备信息
     * @param jsonObject json对象
     * @throws IOException
     */
    public void uploadList(JSONObject jsonObject) throws IOException {
        String controlId = (String) jsonObject.get("controlId");
        saveClient(controlId);
        JSONArray jsonArray = jsonObject.getJSONArray("list");

        //解析设备信息
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject devsJson = jsonArray.getJSONObject(i);
            String devId = (String) devsJson.get("devId");
            String devType = (String) devsJson.get("devType");
            String status = (String) devsJson.get("status");
            DevicePO devicePO = new DevicePO();
            devicePO.setDeviceId(devId);
            devicePO.setDeviceTypePO(new DeviceTypePO(devType));
            devicePO.setStatus(Integer.parseInt(status));
            //存入数据库
            deviceService.addDevice(devicePO);
        }


        OutputStream out = client.getOutputStream();
        JSONObject sendJson = new JSONObject();
        sendJson.accumulate("op","uploadList");
        sendJson.accumulate("result","ok");
        out.write(sendJson.toString().getBytes());
    }

    /**
     * 接收设备控制信息的响应
     * @param jsonObject json对象
     */
    public void control(JSONObject jsonObject){
        String controlId = (String) jsonObject.get("controlId");
        saveClient(controlId);
        String devId = (String) jsonObject.get("devId");
        String result = (String) jsonObject.get("result");
        String status = (String) jsonObject.get("status");

        DevicePO devicePO = deviceService.getByDevId(devId);
        devicePO.setStatus(Integer.parseInt(status));
        deviceService.updateDev(devicePO);
        //
        //更新操作
        //
    }

    public void saveClient(String conId){
        if(!AttendanceServerServlet.getClientList().containsKey(conId)){
            AttendanceServerServlet.getClientList().put(conId,client);
        }
    }

    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        deviceService = context.getBean(DeviceService.class);
    }
}
