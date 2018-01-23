package com.yejunfeng.service.Impl;

import com.yejunfeng.PO.*;
import com.yejunfeng.VO.CourseScheduleVO;
import com.yejunfeng.dao.*;
import com.yejunfeng.server.MessageTypeEnum;
import com.yejunfeng.server.ReceiveFromAtten;
import com.yejunfeng.server.SendToAtten;
import com.yejunfeng.service.CourseScheduleService;
import com.yejunfeng.util.CourseSchedule;
import com.yejunfeng.util.JSONAnalysis;
import com.yejunfeng.util.ObjectToJSON;
import com.yejunfeng.util.ScheduleTime;
import net.sf.json.JSONObject;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService{

    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private CourseScheduleDao csd;
    @Autowired
    private CourseScheduleTimeDao cstd;
    @Autowired
    private AttendanceMachineDao amd;
    @Autowired
    private DozerBeanMapper mapper;

    private List<CourseScheduleTimePO> csts;
    private List<CourseScheduleVO> courseScheduleVOS;
    private List<AttendanceMachinePO> ams;


    public void saveCourseSchedule(List<CourseSchedule> couStr) throws Exception {
//        JSONAnalysis jsonAnalysis = new JSONAnalysis(_url);
//        List<CourseSchedule> couStr = jsonAnalysis.analysis(jsonAnalysis.getResponseMsg());

        /**
         * 存储课表计划
         */
        for(CourseSchedule cs : couStr){
            CourseSchedulePO csPo = new CourseSchedulePO();
            csPo.setCourseScheduleId(cs.getPlanId());
            csPo.setRange(cs.getRange());
            csPo.setTerm(cs.getTerm());
            //加班级外键
            csPo.setClassesPO(classesDao.searchById(cs.getSquadId()));
            //加课程外键
            csPo.setCoursePO(courseDao.searchById(cs.getCourseId()));
            //加教师外键
            csPo.setTeacherPO(teacherDao.searchById(cs.getTchId()));
            csd.addCoursePlan(csPo);
            List<ScheduleTime> times = cs.getTime();
            System.out.println(times.toString());

            for(ScheduleTime st : times){
                CourseScheduleTimePO cstPo = new CourseScheduleTimePO();
                cstPo.setDayOfWeek(st.getDayOfWeek());
                cstPo.setStartWeek(st.getStartWeek());
                cstPo.setEndWeek(st.getEndWeek());
                cstPo.setStartLesson(st.getStartLesson());
                cstPo.setEndLesson(st.getEndLesson());
                cstPo.setMode(st.getMode());

                cstPo.setClassroomPO(classroomDao.searchById(st.getRoomId()));
                cstPo.setCourseSchedulePO(csPo);
                cstd.addCourseScheduleTime(cstPo);
            }

//            System.out.println(cs.getSquadId());

        }
    }

    @Override
    public void readCouSchFromFile(MultipartFile file) throws Exception {
        InputStream in = file.getInputStream();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        while((len=in.read(buff)) != -1){
            bout.write(buff,0,len);
        }
        String str = bout.toString("UTF-8");
        String tempStr = str.substring(0,str.length());
//        System.out.println(str);
        JSONAnalysis jsonAnalysis = new JSONAnalysis();
        saveCourseSchedule(jsonAnalysis.analysis(str));
    }

    public List<CourseScheduleVO> findByRoomIdForTerm(String roomId, String range, int term) {
        csts = cstd.searchByRoomId(roomId,range,term);
        courseScheduleVOS = new ArrayList<CourseScheduleVO>();
        for(CourseScheduleTimePO cst : csts){
            CourseScheduleVO courseScheduleVO = mapper.map(cst,CourseScheduleVO.class);

            //设置该课程在当前周相对星期一的偏移秒数
            int offsetDay = (cst.getDayOfWeek()-1)*24*60*60;
            int time = 0;
            if(cst.getStartLesson() == 1){
                time = offsetDay + 8*3600 + 10*60;
            }else if(cst.getStartLesson() == 3){
                time = offsetDay + 10*60*60 + 10*60;
            }else if(cst.getStartLesson() == 5){
                time = offsetDay + 14*60*60 + 30*60;
            }else if(cst.getStartLesson() == 7){
                time = offsetDay + 16*60*60 + 30*60;
            }else if(cst.getStartLesson() == 9){
                time = offsetDay + 19*60*60 + 30*69;
            }
            courseScheduleVO.setTime(String.valueOf(time));
            courseScheduleVOS.add(courseScheduleVO);
        }
        return courseScheduleVOS;
    }

    public List<ClassroomPO> findAllClassroom() {

        return classroomDao.searchAll();
    }

    public void sendMsgToAtten(String roomId, String range, int term) {
        courseScheduleVOS = findByRoomIdForTerm(roomId,range,term);
        //课程时间安排表转换为json数据
//        ObjectToJSON objectToJSON = new ObjectToJSON();
//        String msg = objectToJSON.couSchToJson(courseScheduleVOS);
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("action","downCourse");
        jsonObject.accumulate("times",new ObjectToJSON().couSchToJson(courseScheduleVOS));
        String msg = jsonObject.toString();

        //获取该课室的考勤机信息
        ams = amd.searchByRoomId(roomId);
        System.out.println(ams.size());
        //每一个考勤机创建一个线程
        for(AttendanceMachinePO am : ams){
            try {
                //实际应用中port应用am.getPort()获取
//                Socket localSocket = new Socket("172.16.11.47",9095);
                Socket localSocket = new Socket("127.0.0.1",9090);
                new Thread(new SendToAtten(MessageTypeEnum.SEND_COURSE_SCHE,msg,localSocket)).start();

//                //开启一个线程，接收考勤机的响应信息
//                new Thread(new ReceiveFromAtten(localSocket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
