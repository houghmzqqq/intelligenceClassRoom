package dao;

import com.yejunfeng.PO.AttendanceMachinePO;
import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.dao.AttendanceMachineDao;
import com.yejunfeng.dao.AttendanceRecordDao;
import com.yejunfeng.dao.ClassesDao;
import com.yejunfeng.dao.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private AttendanceMachineDao amd;
    @Autowired
    private AttendanceRecordDao ard;

    @Test
    @Transactional
    @Rollback(false)
    public void test(){
        ClassesPO classesPO = classesDao.searchById("sqd14rj01");
        System.out.println(classesPO);
        for(int i=1;i<=5;i++){
            StudentPO studentPO = new StudentPO("20141170140"+i,"张三"+1,"男",classesPO);
            studentDao.addStudent(studentPO);
        }

        List<StudentPO> studentPOS = studentDao.searchByClassId("sqd14rj01");
        for(StudentPO student : studentPOS){
            System.out.println(student);
        }
    }

    @Test
    @Transactional
    public void classesTest(){
        List<ClassesPO> classesPOS = classesDao.searchAll();

        for(ClassesPO classesPO : classesPOS){
            System.out.println(classesPO);
        }

    }

    @Test
    @Transactional
    public void divideTest(){
        DividePageVO dividePageVO = studentDao.searchByDivide(2,10);
        for (StudentPO studentPO : dividePageVO.getStudentPOS()){
            System.out.println(studentPO);
        }
    }

    @Test
    @Transactional
    public void attenTest(){
        List<AttendanceMachinePO> ams = amd.searchByRoomId("ss101");
        System.out.println(ams);
    }

    @Test
    @Transactional
    public void recordTest(){
//        "吴梦雪"
        DividePageVO dividePage =  ard.searchByStuId(1,5);
        System.out.println(dividePage.getStudentRecordVOS());
    }

    @Test
    @Transactional
    public void test02(){
        DividePageVO dividePage = classesDao.searchForDivide(1,5);
        System.out.println(dividePage.getClassesPOS());
    }
}
