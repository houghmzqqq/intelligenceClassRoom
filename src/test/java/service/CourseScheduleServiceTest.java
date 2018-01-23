package service;

import com.yejunfeng.PO.AttendanceMachinePO;
import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.CourseScheduleVO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.server.MessageTypeEnum;
import com.yejunfeng.server.SendToAtten;
import com.yejunfeng.service.AttendanceService;
import com.yejunfeng.service.CourseScheduleService;
import com.yejunfeng.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class CourseScheduleServiceTest {

    @Autowired
    private CourseScheduleService courseScheduleService;
    @Autowired
    private StudentService  studentService;
    @Autowired
    private AttendanceService attendanceService;

    @Test
    @Transactional
    @Rollback(false)
    public void test01() throws Exception {
//        courseScheduleService.saveCourseSchedule();
    }

    @Test
    @Transactional
    public void divideTest(){
        DividePageVO di = studentService.getDividePageView(1,10,"0");
        for(StudentPO studentPO : di.getStudentPOS()){
            System.out.println(studentPO);
        }
    }

    @Test
    @Transactional
    public void test02(){
        List<CourseScheduleVO> courseScheduleVOS = courseScheduleService.findByRoomIdForTerm("ss101","2017-2018",1);
        System.out.println(courseScheduleVOS);
    }

    @Test
    @Transactional
    public void test03() throws IOException {
        courseScheduleService.sendMsgToAtten("ss101","2017-2018",1);
//        Socket localSocket = new Socket("172.16.11.47",9095);

//        Socket localSocket = new Socket("127.0.0.1",9090);

//        String msg = "{\"action\":\"downCourse\",\"times\":[{\"time\":29400,\"squadId\":\"wl1401\"," +
//                "\"startWeek\":1,\"endWeek\":5,\"planId\":\"plan01\",\"dayOfWeek\":1},{\"time\":36900," +
//                "\"squadId\":\"wl1302\",\"startWeek\":8,\"endWeek\":9,\"planId\":\"plan03\",\"dayOfWeek\":1}]}";
//        new Thread(new SendToAtten(MessageTypeEnum.SEND_COURSE_SCHE,msg,localSocket)).start();

    }

    @Test
    @Transactional
    public void amServiceTest(){
        List<AttendanceMachinePO> amPOS = attendanceService.findAll();
        System.out.println(amPOS);
    }

    @Test
    @Transactional
    public void sendUrlTest(){
        AttendanceMachinePO amPO = new AttendanceMachinePO("atten01","127.0.0.1","9090",null);
        attendanceService.sendMyUrlToAttend(amPO);
    }

    @Test
    public void photoTest() throws Exception {
        byte[] photo = studentService.getStudentImg("wl141_01");

        ByteArrayInputStream bin = new ByteArrayInputStream(photo);
        FileOutputStream out = new FileOutputStream("D:\\photoWl01.jpg");

        System.out.println("photo length : "+photo.length);

        byte[] buff = new byte[1024];
        int len = -1;

        while((len=bin.read(buff)) != -1){
            out.write(buff,0,len);
        }


    }
}
