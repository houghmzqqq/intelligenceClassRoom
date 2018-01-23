package com.yejunfeng.rfid.Impl;


import com.yejunfeng.rfid.SerialCallBack;

public class SerialCallBackImpl implements SerialCallBack {

	private byte[] data;
	private Client client;
	
	@Override
	public void onReceiveData(byte[] buff) {
		try {
			setData(buff);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		prtArrayMain(buff);
	}
	
	public byte[] getData() {
		return data;
	}
	
	/**
	 *注册一个client
	 *@param name client
	 */
	public void registerObserver(Client client){
		this.client = client;
	}
	
	/**
	 *接收从SerialDevice返回的数据，通知client更新
	 *@param data 从端口读取的数据
	 *@exception InterruptedException
	 */
	public void setData(byte[] data) throws InterruptedException{
		this.data = data;
		client.updateData(data);
	}
}
