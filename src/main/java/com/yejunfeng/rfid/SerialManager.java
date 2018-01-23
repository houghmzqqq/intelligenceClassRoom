package com.yejunfeng.rfid;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;


import com.yejunfeng.exception.*;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class SerialManager {
    /**
     * 查找所有可用端口
     * @return 可用端口名称列表
     */
    @SuppressWarnings("unchecked")
    public static final ArrayList<String> findPort() {
        // 获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier
                .getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<String>();
        // 将可用串口名添加到List并返回该List
        while ( portList.hasMoreElements() ) {
            String portName = portList.nextElement().getName();
            portNameList.add( portName );
        }
        return portNameList;
    }

    /**
     * 打开串口
     * @param portName    端口名称
     * @param baudrate    波特率
     * @return            串口对象
     * @throws ParameterException   设置串口参数失败
     * @throws NotSerialPortException  端口指向设备不是串口类型
     * @throws NoSuchPortException  没有该端口对应的串口设备
     * @throws PortInUseException   端口已被占用
     */
    public static final SerialPort openPort(String portName, int baudrate)
            throws ParameterException, NotSerialPortException,
            NoSuchPortException, PortInUseException {
        try {
            //[1] 通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier
                    .getPortIdentifier(portName);
            //[2] 打开端口, 设置端口名与 timeout(打开操作的超时时间)
            CommPort commPort = portIdentifier.open(portName, 2000);
            //[3] 判断是不是串口
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                try {
                    // 设置串口的波特率等参数
                    serialPort.setSerialPortParams( baudrate,
                            SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE );
                } catch (UnsupportedCommOperationException e) {
                    throw new ParameterException();
                }
                return serialPort;
            } else {
                throw new NotSerialPortException(); // 不是串口
            }
        } catch (NoSuchPortException e1) {
            throw new NoSuchPortException();
        } catch (PortInUseException e2) {
            throw new PortInUseException();
        }
    }

    /**
     * closePort 关闭串口
     * @param serialport  待关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * sendToPort 向串口发送数据
     * @param serialPort  串口对象
     * @param order  待发送数据
     * @throws WriteException 向串口发送数据失败
     * @throws OutputCloseException 关闭串口对象的输出流出错
     */
    public static void sendToPort(SerialPort serialPort, byte[] data)
            throws WriteException, OutputCloseException {
        OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write( data );
            out.flush();
        } catch (IOException e) {
            throw new WriteException();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                throw new OutputCloseException();
            }
        }
    }

    /**
     * readFromPort 从串口读取数据
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据  ---> byte[]
     * @throws ReadException 从串口读取数据时出错
     * @throws InputCloseException 关闭串口对象输入流出错
     */
    public static byte[] readFromPort(SerialPort serialPort)
            throws ReadException, InputCloseException {
        InputStream in = null;
        byte[] bytes = null;
        try {
            in = serialPort.getInputStream();
            // 获取buffer里的数据长度
            int bufflenth = in.available();
            while (bufflenth != 0) {
                // 初始化byte数组为buffer中数据的长度
                bytes = new byte[bufflenth];
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            throw new ReadException();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                throw new InputCloseException();
            }
        }
        return bytes;
    }

    /**
     * addListener 添加监听器
     * @param port 串口对象
     * @param listener 串口监听器
     * @throws TooManyListeners  监听类对象过多
     */
    public static void addListener(SerialPort port,
            SerialPortEventListener listener) 
            		throws ListenerException {
        try {
            //[1] 给串口添加监听器
            port.addEventListener(listener);
            prt("串口侦听器 ---> addListener");
            //[2] 设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //[3] 设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {
            throw new ListenerException();
        }
    }
    
    public static void prt( String str ){
    	System.out.println("[SerialManager] "+ str );
    }
}
