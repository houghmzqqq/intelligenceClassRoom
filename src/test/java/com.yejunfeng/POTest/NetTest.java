package com.yejunfeng.POTest;

import com.yejunfeng.util.JSONAnalysis;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetTest {
    URL url = null;
    HttpURLConnection conn = null;

    /**
     * 打开连接
     * @param _url 网页url
     * @throws MalformedURLException
     */
    public void openConnection(String _url) throws MalformedURLException {
        url = new URL(_url);
    }

    /**
     * 进行连接，设置请求头
     * @throws IOException
     */
    public void doConnect() throws IOException {
        //URLConnection
        conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("POST");  //相当于: method=POST
        conn.setDoOutput( true );       //提交表单的参数 ---> true

        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
    }

    /**
     * 读取请求中的内容
     * @throws IOException
     */
    public void getResponseMsg() throws IOException {
        int code = conn.getResponseCode();
        File file = new File(url.getFile());
        InputStream in = null;
        byte[] buff = new byte[conn.getContentLength()];
        if(code==HttpURLConnection.HTTP_OK){
            in = conn.getInputStream();
            int num;
            int c = 0;
            while((num=in.read()) != -1){
                buff[c] = (byte) num;
                c++;
            }
            String msg = new String(buff,0,conn.getContentLength());
            String msg01 = msg.substring(1,msg.length());
            System.out.println(msg01.charAt(0));
            System.out.println(msg01);
        }
    }

    public static void main(String[] args) throws Exception {
        NetTest test = new NetTest();
        String _url = "http://172.16.12.167:8080/teach/"+
                "interface/course.json";
        test.openConnection(_url);
        test.doConnect();
        test.getResponseMsg();
    }

    @Test
    public void test03() throws Exception {
        JSONAnalysis jsonAnalysis = new JSONAnalysis("http://172.16.12.167:8080/teach/"+
                "interface/course.json");
        jsonAnalysis.analysis("");
    }
}
