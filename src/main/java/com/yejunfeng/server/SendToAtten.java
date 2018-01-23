package com.yejunfeng.server;

import java.net.Socket;

/**
 * 向考勤机发送信息的线程，每一次向每一个考勤机发送信息都
 * 需要创建一个线程，发送完之后关闭线程
 */
public class SendToAtten implements Runnable {

    private MessageTypeEnum messageTypeEnum;
    private String msg;
    private Socket localSocket;

    public SendToAtten(MessageTypeEnum messageTypeEnum, String msg,Socket localSocket) {
        this.messageTypeEnum = messageTypeEnum;
        this.msg = msg;
        this.localSocket = localSocket;
    }

    public void run() {
        new HandleMessage(msg,localSocket).chooseMethod(messageTypeEnum);

    }
}
