package com.yejunfeng.util;

import com.yejunfeng.dao.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONAnalysis {

    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private CourseScheduleDao csd;
    @Autowired
    private CourseScheduleTimeDao cstd;

    private URL url;
    private HttpURLConnection conn;
    private String _url;

    public JSONAnalysis(){
//        this._url = "http://172.16.12.167:8080/teach/"+
//                "interface/course.json";
//        init();
    }

    public JSONAnalysis(String _url) throws Exception {
        this._url = _url;
        init();
    }

    /**
     * 初始化，连接url
     * @throws Exception
     */
    public void init() throws Exception {
        openConnection(_url);
        doConnect();
    }

    /**
     * 解析json数据
     * @throws IOException
     * @return 课表计划的list集合
     */
    public List<CourseSchedule> analysis(String msg) throws IOException {
//        String msg = getResponseMsg();

        JSONObject jsonObject = JSONObject.fromObject(msg);
        String range = jsonObject.getString("range");
        String termTem = jsonObject.getString("term");
        int term = Integer.valueOf(termTem);

        //获取json数据中的课表计划list
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("time",ScheduleTime.class);
        JSONArray jsonArray = jsonObject.getJSONArray("course");
        List<CourseSchedule> couList = (List<CourseSchedule>) JSONArray.toList(jsonArray,CourseSchedule.class,map);

        for(CourseSchedule cs : couList){
            cs.setRange(range);
            cs.setTerm(term);
        }
        return couList;
    }

    /**
     * 打开连接
     * @param _url 网页url
     * @throws MalformedURLException
     */
    public void openConnection(String _url) throws MalformedURLException {
        this.url = new URL(_url);
    }

    /**
     * 进行连接，设置请求头
     *
     * @throws IOException
     */
    public void doConnect() throws IOException {
        //URLConnection
        conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("POST");  //相当于: method=POST
        conn.setDoOutput(true);       //提交表单的参数 ---> true

        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
    }

    /**
     * 读取请求中的内容
     *
     * @throws IOException
     */
    public String getResponseMsg() throws IOException {
        String msg = "";
        int code = conn.getResponseCode();

        File file = new File(url.getFile());
        InputStream in = null;

        byte[] buff = new byte[conn.getContentLength()];
        if (code == HttpURLConnection.HTTP_OK) {
            in = conn.getInputStream();
            int num;
            int c = 0;
            while ((num = in.read()) != -1) {
                buff[c] = (byte) num;
                c++;
            }
            msg = new String(buff, 0, conn.getContentLength());
        }
        return msg.substring(1,msg.length());
    }

    public String get_url() {
        return _url;
    }
    public void set_url(String _url) throws Exception {
        this._url = _url;
        openConnection(_url);
        doConnect();
    }

}
