package network;

import org.omg.CORBA.portable.OutputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements Runnable {

    private static Vector<Socket> client_lists = new Vector<Socket>();

    public void run() {
        try {
            ServerSocket server = new ServerSocket(9090);
            while(true){
                Socket client;
                if((client=server.accept()) != null){
                    client_lists.add(client);
                    handleClientRequest(client);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClientRequest(Socket client) throws IOException{
        DataInputStream input = null;
        OutputStream out = null;
        try {
            //获取客户端输入流
            byte[] buff = new byte[1024];
            input = new DataInputStream(client.getInputStream());
            int i = input.readInt();
            String msg = input.readUTF();
            if(i==1){
                System.out.println("[校验信息]: 接收课表信息请求！");
            }
            System.out.println(msg);
//            int count = -1;
//            int i = 0;
//            while((count=input.read()) != -1){
//                buff[i] = (byte) count;
//                i++;
//            }
//            if(buff[0] == 100){
//                System.out.println("[校验信息]: 接收课表信息请求！");
//            }else if(buff[0] == 101){
//                System.out.println("[校验信息]: 设置开学周周一请求！");
//            }
//            System.out.println(new String(buff,1,i));

//            int count = -1;
//            int index = 1;
//            int length = 0;
//            while((count=input.read()) != -1){
//                if(index == 1 && count == 66){
//                    index++;
//                    continue;
//                }else if(index == 1 && count != 66){
//                    break;
//                }
//                buff[length] = (byte) count;
//            }
//            String str = new String(buff,0,length);
//            System.out.println(str);
        } finally {
            input.close();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Server());
        thread.start();
    }
}
