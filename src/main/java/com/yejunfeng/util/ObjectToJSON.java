package com.yejunfeng.util;

import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.CourseScheduleVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ObjectToJSON {

    private String jsonStr;

    /**
     * 将学生信息转换为Json字符串
     * @param studentPOS 学生信息集合
     * @return json字符串
     */
    public String studentToJson(List<StudentPO> studentPOS){
        List<StudentForAtten> sis = new ArrayList<StudentForAtten>();

        for(StudentPO sPo : studentPOS){
            StudentForAtten si = new StudentForAtten();
            si.setId(sPo.getStudentId());
            si.setStuName(sPo.getStudentName());
            si.setRfid(sPo.getRFID());
            sis.add(si);
        }

        JSONArray jsonArray = JSONArray.fromObject(sis);
        jsonStr = jsonArray.toString();
        return jsonStr;
    }

    /**
     * 将课程时间安排表转换为json
     * @param courseScheduleVOS 课程安排表
     * @return json字符串
     */
    public String couSchToJson(List<CourseScheduleVO> courseScheduleVOS){
        JSONArray jsonArray = JSONArray.fromObject(courseScheduleVOS);
        jsonStr = jsonArray.toString();
        return jsonStr;
    }

//    /**
//     * 将相片转化为字符串
//     * @param photo Blob类型的头像
//     * @return
//     */
//    public String photoToString(Blob photo){
//        String photoStr = null;
//        byte[] datas = null;
//        InputStream in = null;
//        ByteOutputStream bout = null;
//        try {
//            //转化为数组
//            in = photo.getBinaryStream();
//            bout = new ByteOutputStream();
//            byte[] buff = new byte[1024];
//            int len = -1;
//            while((len=in.read(buff)) != -1){
//                bout.write(buff,0,len);
//            }
//            datas = bout.getBytes();
//            photoStr = datas.toString();
////            photoStr = compress(datas);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                in.close();
//                bout.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return photoStr;
//    }
//
//    /**
//     * 压缩相片
//     * @param data 头像的字节数据
//     * @return 经过压缩的头像转化为字符串
//     * @throws IOException
//     */
//    public String compress(byte[] data) throws IOException {
//        String dataStr = new String(data,"ISO-8859-1");
//        data = dataStr.getBytes("ISO-8859-1");
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        GZIPOutputStream gout = new GZIPOutputStream(bout);
//
//        gout.write(data);
//        gout.finish();
//        gout.close();
//
//        String finalStr = bout.toString("ISO-8859-1");
//
//        return finalStr;
//    }
}
