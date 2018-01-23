package com.yejunfeng.rfid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yejunfeng.exception.*;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialDevice implements SerialPortEventListener {  
    
    static Set<CommPortIdentifier> portList = new HashSet<CommPortIdentifier>();  
    private SerialPort serialport;
    
    byte[] readBuff = new byte[100];
    SerialCallBack callBack;
    public void setCallBack(SerialCallBack _callBack){
    	this.callBack = _callBack;
    }
    
    /*
                     功能介绍: 查找串口设备, 如果找到会存入  rsList。
                     函数名称: findSerial
                    返回 String: com7
     */
    public String findSerial() {
    	List<String> rsList = null;
    	rsList = SerialManager.findPort();
        //PS: 检查是否有可用串口, 有则加入 --> List<String>。
        if (rsList!=null && rsList.size()!=0) {
        	System.out.printf( "[SerialDevice] 成功搜索到串口设备: [%s]\n", rsList.get(0) );
            return rsList.get(0);
        }
        prt( "没有搜索到有效串口设备!" );
        return null;
    }

    /*
     	功能介绍: 
     	  [1] 打开串口设备。
     	  [2] 并添加侦听事件。
     	  [3] 一有数据反应立即回调本类的 "serialEvent" 方法。
     	函数名称: openSerialDevice
     	传入串口设备名: 如 com5
     	默认波特率: 9600 Hz
    */
    public void openSerialDevice(String devName){
        //[2] 设置波特率: 9600 HZ
        int baudrate = 9600;
        // 检查串口名称是否获取正确
        if (devName==null || devName.equals("")) {
            prt("没有搜索到有效串口设备!");
        } else {
            try {
				serialport = SerialManager.openPort(devName, baudrate);
			} catch (ParameterException | NotSerialPortException
					| NoSuchPortException | PortInUseException e) {
				e.printStackTrace();
			}
            if (serialport != null) {
               prt("串口设备已打开!");
            }
        }
        try {
    	    SerialManager.addListener(serialport, this);
    	    prt("打开串口事件侦听..");
		} catch (ListenerException e) {
			e.printStackTrace();
		}
    }

	//[2] 关闭串口 [closeSerialPort]
	public void closeSerialPort(){
		SerialManager.closePort(serialport);
		prt("串口设备已关闭!");
	}
	
	//[2] 写数据到串口 [sendData]
	public void sendData(String data){
        System.out.println("[SerialDevice] sendData:"+ data);
        if( serialport==null ){
        	prt( "串口设备不存在。" );
        	return;
        }
        try {
			SerialManager.sendToPort(
				  serialport, ByteUtils.hexStrToByte( data ) );
		} catch (WriteException | OutputCloseException e) {
			e.printStackTrace();
		} finally{
			
		}
	}
	
	public void sendData(byte[] buff){
        if( serialport==null ){
        	prt( "串口设备不存在。" );
        	return;
        }
        try {
			SerialManager.sendToPort( serialport, buff );
		} catch (WriteException | OutputCloseException e) {
			e.printStackTrace();
		} finally{
			
		}
	}

    private void prt(String str) {
		System.out.println( "[SerialDevice] "+ str );
	}

    /*  -------------------------  串口事件侦听器  ----------------------------- */
	@Override
	public void serialEvent(SerialPortEvent event) {
//		prt( "(238) 发生串口事件.." );
        switch ( event.getEventType() ) {
            case SerialPortEvent.BI:  // 10 通讯中断
                prt( "与串口设备通讯中断" );
            break;
            case SerialPortEvent.OE:  // [7] 溢位(溢出)错误
            case SerialPortEvent.FE:  // [9] 帧错误
            case SerialPortEvent.PE:  // [8] 奇偶校验错误
            case SerialPortEvent.CD:  // [6] 载波检测
            case SerialPortEvent.CTS: // [3] 清除待发送数据
            case SerialPortEvent.DSR: // [4] 待发送数据准备好了
            case SerialPortEvent.RI:  // [5] 振铃指示
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
            break;
            case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                byte[] data = null;
                try {
                    if (serialport==null) {
                        prt("串口对象为空, 监听失败!");
                    } else {
                        data = SerialManager.readFromPort(serialport);
                        if( callBack!=null ){
                        	 callBack.onReceiveData( data );
                        }
                    }
                } catch (Exception e) {
                    prt( e.toString() );
                    System.exit(0);
                }
            break;
        }
	}

}
