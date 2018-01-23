package com.yejunfeng.POTest;

import com.yejunfeng.PO.*;
import com.yejunfeng.dao.ClassesDao;
import com.yejunfeng.dao.CourseDao;
import com.yejunfeng.service.CourseScheduleService;
import com.yejunfeng.util.JSONAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class PoTset {

    @Autowired
    HibernateTemplate hibernateTemplate;
    @Resource
    private ClassesDao classesDao;
    @Autowired
    private CourseDao courseDao;


    @Test
    @Transactional
    @Rollback(false)
    public void test() throws Exception {
        File file = new File("C:\\Users\\handsome-boy\\Desktop\\head.jpg");
        FileInputStream in = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream("D:\\head.jpg");
        byte[] photo = new byte[(int)file.length()];
        try {
            int number;
            int index = 0;
            while((number = in.read()) != -1){
                photo[index] = (byte)number;
                index++;
            }
            out.write(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
        }

//        StudentInfoPO studentInfoPO = new StudentInfoPO("201411701402","李四","软件1144","嵌入式系统开发");
//        studentInfoPO.setPhoto(photo);
//        hibernateTemplate.save(studentInfoPO);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test02() {
        for(int i=0;i<5;i++){
            UserPO userPO = new UserPO("u00"+i,"jock"+i,"account"+i,"123"
                    ,"管理员",null);
//            DevicePO devicePO = new DevicePO("dev"+i,"照明设备","日光灯","第"+i+"排照明灯"
//                    ,null,"undefine");
            hibernateTemplate.save(userPO);
//            hibernateTemplate.save(devicePO);
        }

    }

    /**
     * 添加课表计划所需要的基本数据
     */
    @Test
    @Transactional
    @Rollback(false)
    public void addInfo(){
        ClassesPO classesPO;

        TeacherPO teacherPO = new TeacherPO("tch01","英语教授","李朝胜","数学与计算机学院");
        hibernateTemplate.save(teacherPO);
        teacherPO = new TeacherPO("tch03","英语教授","刘利民","数学与计算机学院");
        hibernateTemplate.save(teacherPO);
        teacherPO = new TeacherPO("tch05","英语教授","周福民","数学与计算机学院");
        hibernateTemplate.save(teacherPO);


//        classesPO = new ClassesPO("sqwl1302","物联1132","物联网",2013);
//        hibernateTemplate.save(classesPO);
        classesPO = new ClassesPO("sqwl1401","物联1141","物联网",2014);
        hibernateTemplate.save(classesPO);
        classesPO = new ClassesPO("sqwl1402","物联1142","物联网",2014);
        hibernateTemplate.save(classesPO);

        CoursePO coursePO = new CoursePO("crs12","软件综合实训");
        hibernateTemplate.save(coursePO);
        coursePO = new CoursePO("crs13","Java程序设计");
        hibernateTemplate.save(coursePO);
        coursePO = new CoursePO("crs03","C++程序设计");
        hibernateTemplate.save(coursePO);

        ClassroomPO amPo = new ClassroomPO("ss101","实训楼101教室");
        hibernateTemplate.save(amPo);
        amPo = new ClassroomPO("ss102","实训楼102教室");
        hibernateTemplate.save(amPo);
        amPo = new ClassroomPO("zh201","综合楼201教室");
        hibernateTemplate.save(amPo);
        amPo = new ClassroomPO("zh202","综合楼202教室");
        hibernateTemplate.save(amPo);
        amPo = new ClassroomPO("ss301","实训楼301教室");
        hibernateTemplate.save(amPo);


    }

    @Test
    @Transactional
    @Rollback(false)
    public void addDevice(){
        DeviceTypePO deviceTypePO = new DeviceTypePO("devType01","日光灯");
        deviceTypePO = new DeviceTypePO("devType02","风扇");
        hibernateTemplate.save(deviceTypePO);
        deviceTypePO = new DeviceTypePO("devType03","温度传感器");
        hibernateTemplate.save(deviceTypePO);
        deviceTypePO = new DeviceTypePO("devType04","光照传感器");
        hibernateTemplate.save(deviceTypePO);
        deviceTypePO = new DeviceTypePO("devType05","烟雾传感器");
        hibernateTemplate.save(deviceTypePO);
        deviceTypePO = new DeviceTypePO("devType06","红外");
        hibernateTemplate.save(deviceTypePO);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void addToRoom(){
        ClassroomPO classroomPO = new ClassroomPO();
        AttendanceMachinePO am = null;
        classroomPO.setRoomId("ss101");
        am = new AttendanceMachinePO("atten01","127.0.0.1","9090",classroomPO);
        hibernateTemplate.save(am);
        classroomPO.setRoomId("ss102");
        am = new AttendanceMachinePO("atten02","127.0.0.1","9092",classroomPO);
        hibernateTemplate.save(am);
        classroomPO.setRoomId("ss301");
        am = new AttendanceMachinePO("atten03","127.0.0.1","9093",classroomPO);
        hibernateTemplate.save(am);
        classroomPO.setRoomId("zh201");
        am = new AttendanceMachinePO("atten04","127.0.0.1","9094",classroomPO);
        hibernateTemplate.save(am);
        classroomPO.setRoomId("zh202");
        am = new AttendanceMachinePO("atten05","127.0.0.1","9095",classroomPO);
        hibernateTemplate.save(am);

//        ClassesPO classesPO = new ClassesPO("sqd13wl02","物联1132","物联网",2013);
//        hibernateTemplate.save(classesPO);
//        classesPO = new ClassesPO("sqd14wl01","物联1141","物联网",2014);
//        hibernateTemplate.save(classesPO);
//        classesPO = new ClassesPO("sqd14wl02","物联1142","物联网",2014);
//        hibernateTemplate.save(classesPO);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void analysisJson() throws Exception {
        new JSONAnalysis("http://172.16.10.103:8080/teach/interface/course.json").analysis("");
        ClassesPO classesPO = classesDao.searchById("sqd14rj02");
        System.out.println( classesPO.getClassName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getFromDao() throws ParseException {
//        ClassesPO classesPO = classesDao.searchById("sqd14rj02");
//        System.out.println(classesPO.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,9,4,0,0,0);
        System.out.println(calendar.getTimeInMillis());
    }

    /**
     * 增加课表
     */
    @Test
    @Transactional
    @Rollback(false)
    public void addCourseSchedule(){

    }

    @Test
    @Transactional
    public void test04() throws IOException {
        Socket client = new Socket("172.16.11.47",9095);

    }
}
