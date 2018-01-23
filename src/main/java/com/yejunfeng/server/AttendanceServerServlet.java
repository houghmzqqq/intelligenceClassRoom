package com.yejunfeng.server;

import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 加载servlet容器时自动启动，生成一个ServerSocket，接收考勤机发送的连接请求并接收数据
 */
public class AttendanceServerServlet extends HttpServlet implements Runnable {


    private static boolean open = true;
    private static int port = 10086;
    private static Map<String,Socket> clientList = new HashMap<>();

    private ServerSocket serverSocket;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * 定义一个接受考勤机消息的线程
     */
    public void run() {
        System.out.println("I am open the server socket");
        try {
            serverSocket = new ServerSocket(port);
            while(open){
                Socket client = serverSocket.accept();
                //每次监听到连接，委托给ReceiveFromAtten处理
                new Thread(new ReceiveFromAtten(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() throws ServletException {
        super.init();
        Thread thread = new Thread(new AttendanceServerServlet());
        thread.start();
    }

    public static void setOpen(boolean open) {
        AttendanceServerServlet.open = open;
    }
    public static void setPort(int port) {
        AttendanceServerServlet.port = port;
    }

    public static Map<String, Socket> getClientList() {
        return clientList;
    }
}
