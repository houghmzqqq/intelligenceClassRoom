package com.yejunfeng.server;

import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.dao.ClassesDao;
import com.yejunfeng.dao.StudentDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class HandleMessage {

    private String msg;
    private Socket client;

    @Autowired
    private StudentDao studentDao;

    public HandleMessage(String msg, Socket client) {
        this.msg = msg;
        this.client = client;
    }

//    public HandleMessage(String msg) {
//        this.msg = msg;
//    }

    /**
     * 选择合适的方法处理消息体
     */
    public void chooseMethod(MessageTypeEnum messageTypeEnum) {
        switch (messageTypeEnum){
            case SEND_URL:
                sendMyUrlToAtten();
                break;
            case SEND_COURSE_SCHE:
                sendCourseSchedule();
                break;
            case SEND_TREM_BEGIN:
                sendTremBeginsTime();
                break;
            case RECEIVE_REGISTRE:
                receiveRegisterRequest();
                break;
            case RECEIVE_ATTEN_DATA:
                receiveAttendanceData();
                break;
            case RECEIVE_SEND_STU:
                receiveSendStuInfo();
                break;
            case RECEIVE_SEND_PHOTO:
                receiveSendPhoto();
                break;
            case MSG_ERROR:
                throw new RuntimeException("无法识别请求！");
        }
    }

    /**
     * 向考勤机发送服务器http请求的url
     */
    public synchronized void sendMyUrlToAtten(){
        OutputStream out = null;
        try {
            out = client.getOutputStream();
            out.write(msg.getBytes());
            System.out.println("[Info]: send URL success.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向考勤机发送课表数据，并接收响应
     */
    public synchronized void sendCourseSchedule(){
        OutputStream out = null;
        String receiveMsg = "";
        try {
            //课表数据写入socket的输出流
            out = client.getOutputStream();
            out.write(msg.getBytes());
            out.flush();
            System.out.println(msg);
//            Thread.sleep(10000);

            //接收考勤机响应
//            new Thread(new ReceiveFromAtten(client)).start();
//            System.out.println(receiveCourseAnswer());

        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("[Info]: 连接超时，接收课表发送结果 响应失败！");
//        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public String receiveCourseAnswer(){
        InputStream in = null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String receiveMsg = "";
        try {
            //读取考勤机的响应
            in = client.getInputStream();
            while(receiveMsg==null || receiveMsg.equals("")){
                int len = -1;
                while((len=in.read()) != -1){
                    bout.write(len);
                }
                receiveMsg = bout.toString();
            }

            JSONObject jsonObject = JSONObject.fromObject(receiveMsg);
            String action = (String) jsonObject.get("action");
            String result = (String) jsonObject.get("result");
            if(action.equals("downCourse") && (result.equals("ok") || result.equals("failed"))){
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return receiveMsg;
    }

    /**
     * 向考勤机发送学生信息
     * @param studentPOS
     */
    public synchronized void sendStudentInfo(List<StudentPO> studentPOS){
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(client.getOutputStream());
            out.write(2);
            out.writeUTF(msg);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sendTremBeginsTime(){

    }

    public synchronized void receiveRegisterRequest(){

    }

    public synchronized void receiveAttendanceData(){

    }

    /**
     * 接收考勤机的请求，向它发送一个班级学生的信息
     */
    public synchronized void receiveSendStuInfo(){
        System.out.println("[receiveSendStuInfo]: I receive a msg!");
        String classId = msg;
        List<StudentPO> studentPOS = studentDao.searchByClassId(classId);
        sendStudentInfo(studentPOS);
    }

    public synchronized void receiveSendPhoto(){

    }

}
