package com.yejunfeng.rfid;

public class ByteUtils {

	public static byte[] hexStrToByte(String data) {
		data = data.replaceAll("-","");
		int size = data.length()/2;
		byte[] buff = new byte[size];
		String str = null;
		byte digit = 0;
		for( int i=0; i<size; i++ ){
			str = data.substring(i*2,i*2+2);
			digit = (byte)Integer.parseInt(str, 16);
			buff[i] = digit;
		}
		return buff;
	}

	public static String bytesToHexStr(byte[] data) {
		//6, 1, 65, 0, 185, 3
		StringBuffer line = new StringBuffer();
		int num = 0;
		for(int i=0; i<data.length; i++){
			num = data[i];
			if(num < 0){
				num = 256 + data[i];
			}
			String hex = intToHexStr( num );
			line.append( hex +"," );
		}
		if( num>0 ){
			line.setLength(line.length()-1);
		}
		return line.toString();
	}
	
	private static String intToHexStr(int num){
		int consult;
		int remain;
		char[] hex = new char[ 20 ];
		int index = 0;
		StringBuffer sb = new StringBuffer();
		do {
			remain = num % 16;
			num = num / 16;
			if(remain > 9){
				hex[index] = (char)((remain-10)+'A');
			}else{
				hex[index] = (char)(remain+'0');
			}
			//prt( hex[index] );
			index ++;
		}while( num!=0 );
		for(int i=index-1; i>=0; i--){
			sb.append( hex[i] );
			//prt( hex[i] );
		}
		return sb.toString();
	}
	
	private static void prt( char ch ){
		if( ch>=65 && ch<65+26 ){
			System.out.printf( "%c\n", ch );
		}else{
			System.out.printf( "%d\n", (int)ch );
		}
	}
	
	//185 ---->B9
	//186 ---->BA
	//187 ---->BB
	public static void main(String[] args) {
		byte[] bytes = hexStrToByte("06014100B903");
		String line  = bytesToHexStr(bytes);
		System.out.println( line );
	}
}
