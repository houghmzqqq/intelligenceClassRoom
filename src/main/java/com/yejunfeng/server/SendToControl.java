package com.yejunfeng.server;

import java.io.IOException;
import java.io.OutputStream;

public class SendToControl implements Runnable{

    private String controlId;
    private String data;

    public SendToControl(String data){
        controlId = "c001";
        this.data = data;
    }

    @Override
    public void run() {
        try {
            OutputStream out = AttendanceServerServlet.getClientList().get(controlId).getOutputStream();
            out.write(data.getBytes());
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
