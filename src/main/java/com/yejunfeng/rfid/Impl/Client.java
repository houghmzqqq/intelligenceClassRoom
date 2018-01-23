package com.yejunfeng.rfid.Impl;

import com.yejunfeng.rfid.RfidModule;
import com.yejunfeng.rfid.SerialDevice;

import java.sql.Date;
import java.util.concurrent.*;


public class Client implements Callable<String>{

	private SerialDevice rsDevice;
	private RfidModule rfidModule;
	private SerialCallBackImpl callBack;
	private byte[] data;
	private String rfid;
	
	public Client(){
		callBack = new SerialCallBackImpl();
		callBack.registerObserver(this);
	}
	
	/**
	 *发送刷卡请求
	 */
	public String call(){
		//创建一个串口通信
		rsDevice = new SerialDevice();
		//设置串口数据回调接口
		rsDevice.setCallBack(callBack);
		//打开串口设备
		String devName = rsDevice.findSerial();
		rsDevice.openSerialDevice(devName);
		
		rfidModule = new RfidModule();
		rfidModule.setSerialDevice(rsDevice);

		while (rfid==null || rfid.equals("")) {
			toPiccRquest();
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return rfid;
	}
	
	public void toPiccRquest(){
			rfidModule.piccRequest();
//			data = callBack.getData();
	}
	
	public void toPiccAnticoll() throws InterruptedException{
//		Thread.sleep(1000);
		rfidModule.piccAnticoll();
//		data = callBack.getData();
//		if(data[2] == 0){
//			prtArray(data);
//			prtArrayMain(data,"from piccAnticoll");
//		}
//		data = null;
//		Thread.sleep(1000);
//		prtArrayMain(data,"from piccAnticoll");
	}
	
	//输出卡号id
	private String prtArray(byte[] buff) {
		System.out.printf( "[Main] ID: 0x" );
		byte[] id = new byte[4];
//		int[] idInt = new int[4];
//		StringBuffer strBuff = new StringBuffer();
		int j = 0;
		for(int i=7;i>=4;i--){
			if( buff[i]>='A' && buff[i]<='Z' || buff[i]>='0' && buff[i]<='9' ){
				System.out.printf( "%c", buff[i] );
			}else{
				System.out.printf( "%02X", buff[i] );
			}
			id[j] = buff[i];
			j++;
		}
//		System.out.println("Id : " + id.toString() );
		System.out.printf( " \n" );
		return bytesToHexStr(id);
	}
	
	public String bytesToHexStr(byte[] id){
		StringBuilder buf = new StringBuilder();
		for(byte b : id){
			buf.append(String.format("%02x", new Integer(b & 0xff)));
		}
		return buf.toString();
	}
	
	//输出串口返回的数据
	private void prtArrayMain(byte[] buff,String moreMsg) {
		System.out.printf( "[Main]"+moreMsg+" prt: " );
		for(byte b : buff){
			if( b>='A' && b<='Z' || b>='0' && b<='9' ){
				System.out.printf( "%c, ", b );
			}else{
				System.out.printf( "%02X, ", b );
			}
		}
		System.out.printf( "\b\b \n" );
	}
	
	/**
	 *监听串口返回的数据
	 *@param name buff 来自SerialCallBack实现类的数据
	 *@return null
	 * @throws InterruptedException 
	 */
	public void updateData(byte[] buff) throws InterruptedException{
//		System.out.println("01");
		if(buff[0] == 8 && buff[2] == 0){
			toPiccAnticoll();
		}else if(buff[0] != 8 && buff[2] != 0){
			toPiccRquest();
		}else if(buff[0] == 10 && buff[2] == 0){
			rfid = prtArray(buff);

//			System.out.println("[client]:刷卡成功，请等待两秒后再进行刷卡！");
			Thread.sleep(2000);
		}else if(buff[0] == 10 && buff[2] != 0){
			System.out.println("[Main]exception: 发生碰撞！");
		}else {
//			System.out.println("[Main]exception: 未发现卡！");
		}
	}
	
//	public static void main(String[] args) throws ExecutionException, InterruptedException {
//		ExecutorService exec = Executors.newCachedThreadPool();
//		Future<String> res = exec.submit(new Client());
//		exec.shutdown();
//		System.out.println("[callable]:  " + res.get());
//	}
}
