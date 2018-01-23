package com.yejunfeng.service.Impl;

import com.yejunfeng.PO.AttendanceMachinePO;
import com.yejunfeng.dao.AttendanceMachineDao;
import com.yejunfeng.exception.AttenMachException;
import com.yejunfeng.server.MessageTypeEnum;
import com.yejunfeng.server.ReceiveFromAtten;
import com.yejunfeng.server.SendToAtten;
import com.yejunfeng.service.AttendanceService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMachineDao amd;

    public List<AttendanceMachinePO> findAll() {
        return amd.searchAll();
    }

    public AttendanceMachinePO findById(String attenId) {
        return amd.searchById(attenId);
    }

    public void addAttendanceMachine(AttendanceMachinePO am) throws AttenMachException {
        amd.addMachine(am);
    }

    public void deleteAttenMach(String attenId) {
        amd.deleteMachine(attenId);
    }

    public void updateAttenMach(AttendanceMachinePO am) {
        amd.updateMachine(am);
    }

    public void sendMyUrlToAttend(AttendanceMachinePO amPO) {
        try {
            String action = "confirmRegister";
            //获取myUrl.properties配置文件中的http请求的url路径
            InputStream in = new FileInputStream(ResourceUtils.getFile("classpath:myUrl.properties"));
            Properties pos = new Properties();
            pos.load(in);
            String attUpload = pos.getProperty("attUpload");
            String getImg = pos.getProperty("getImg");
            String getStudents = pos.getProperty("getStudents");

            //获取配置文件中的开学时间
            in = new FileInputStream(ResourceUtils.getFile("classpath:termBegins.properties"));
            pos.load(in);
            String date = pos.getProperty("termBeginsTime") ;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int termStart = (int) (sdf.parse(date).getTime()/1000);

            //组装成json格式
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("action",action);
            jsonObject.accumulate("termStart",termStart);
            jsonObject.accumulate("attUpload",attUpload);
            jsonObject.accumulate("getImg",getImg);
            jsonObject.accumulate("getStudents",getStudents);


            System.out.println("[Info]: try to send URL to attendance.");
            //开启一个线程，向考勤机发送信息
            Socket localSocke = new Socket(amPO.getIP(),Integer.parseInt(amPO.getPort()));
            new Thread(new SendToAtten(MessageTypeEnum.SEND_URL,jsonObject.toString(),localSocke)).start();

//            //开启一个线程，接收考勤机的响应信息
//            new Thread(new ReceiveFromAtten(localSocke)).start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
