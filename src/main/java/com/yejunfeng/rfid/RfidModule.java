package com.yejunfeng.rfid;

import gnu.io.SerialPort;

public class RfidModule {
	
	public int mode = 0;
	
	public static final int NONE = 0x0;	
	public static final int GET_VERSION = 0x1;
	public static final int READ_CARD = 0x2;
	public static final int ANTICOLL = 0x3;
	
	private byte[] dataBfr = new byte[16];
	SerialDevice device;
	public void setSerialDevice(SerialDevice _device){
		this.device = _device;
	}
	
	/**
	 * 计算  BCC 校验位
	 * n = 帧长 - 2
	 */
	private byte calBCC(byte[] buf, int n){
		int i;
		byte bcc = 0;
		for( i=0; i<n; i++ ){
			bcc ^= buf[i];
		}
		return (byte)(~bcc);
	}
	
	public void showRFIDInfo(){
		String data = "06-01-41-00-B9-03";
		byte[] buff = ByteUtils.hexStrToByte( data );
		write( buff );
		setMode( GET_VERSION );
	}
	
	/**
	 * PiccRequest 请求读天线范围内的  RFID 卡。
	 * 计算  BCC 校验位
	 * n = 帧长 - 2
	 */
	public void piccRequest(){
		byte[] wBuf = new byte[7];
		wBuf[0] = 0x07;	        //[1] 帧长= 7 Byte
		wBuf[1] = (byte)0x02;	//[2] 包号= 0 , 命令类型= 0x02
		wBuf[2] = 0x41;	        //[3] 命令= 'A'
		wBuf[3] = 0x01;	        //[4] 信息长度= 1
		wBuf[4] = 0x52;	        //[5] 请求模式:  ALL=0x52
		wBuf[5] = calBCC(wBuf, wBuf[0]-2);  //[6] 校验和
		wBuf[6] = 0x03; 	    //[7] 结束标志
		write( wBuf );
		setMode( READ_CARD );
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}

	public void piccAnticoll(){
		byte[] wrBuff = new byte[8];
		wrBuff[0] = 0x08;	     //[1] 帧长= 8 Byte
		wrBuff[1] = 0x02;	     //[2] 包号= 0 , 命令类型= 0x01
		wrBuff[2] = 0x42;	     //[3] 命令= 'B'
		wrBuff[3] = 0x02;	     //[4] 信息长度= 2
		wrBuff[4] = (byte)0x93;	 //[5] 防碰撞 0x93  一级防碰撞
		wrBuff[5] = 0x00;	     //[6] 位计数0
		wrBuff[6] = calBCC(wrBuff, wrBuff[0]-2);  //[7]校验和
		wrBuff[7] = 0x03; 	     //[8] 结束标志
		write( wrBuff );
		setMode( ANTICOLL );
	}
	

	private void write(byte[] wBuf) {
//		prtArray( wBuf, "write" );
		device.sendData( wBuf );
	}

	private void prt(String str) {
		System.out.println( str);
	}

	private void prtArray(byte[] buff, String msg) {
		System.out.printf( "[RFID][%s]", msg );
		for(byte b : buff){
			System.out.printf( "%02X, ", b );
		}
		System.out.printf( "\b\b \n" );
	}

	public void anticollOK() {
		setMode( this.NONE );
	}
	
}
