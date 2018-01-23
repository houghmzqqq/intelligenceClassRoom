package service;

import com.yejunfeng.PO.*;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.dao.AttendanceRecordDao;
import com.yejunfeng.exception.RecordSaveException;
import com.yejunfeng.service.AttendanceRecordService;
import com.yejunfeng.service.ClassesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RecordTest
{

    @Autowired
    private AttendanceRecordService attendanceRecordService;
    @Autowired
    private AttendanceRecordDao ard ;
    @Autowired
    private ClassesService classesService;

    @Test
    @Transactional
    @Rollback(false)
    public void test01() throws RecordSaveException {
        AttendanceRecordPO arPO = new AttendanceRecordPO();
        arPO.setCourseSchedulePO(new CourseSchedulePO("plan01"));
        arPO.setStudentPO(new StudentPO("wl141_04"));
        arPO.setClassroomPO(new ClassroomPO("ss101"));
        arPO.setStatus(1);
        arPO.setTime(29400);
        arPO.setDayOfWeek(1);
        arPO.setWeek(2);
        System.out.println(arPO);

        attendanceRecordService.addRecord(arPO);
    }

    public void test02(){

    }

    @Test
    @Transactional
    public void getRecord(){
        DividePageVO dividePageVO = attendanceRecordService.getStuRecordDivide(1,5,null);
        System.out.println(dividePageVO.getStudentRecordVOS());
    }

    @Test
    @Transactional
    public void test03(){
        DividePageVO dividePage = classesService.getClassesDivide(1,5);
        System.out.println(dividePage.getClassesPOS());
    }

}
