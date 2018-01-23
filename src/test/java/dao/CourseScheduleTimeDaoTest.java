package dao;

import com.yejunfeng.dao.ClassesDao;
import com.yejunfeng.dao.CourseScheduleTimeDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class CourseScheduleTimeDaoTest {

//    @Autowired
//    private CourseScheduleTimeDao cstDao;

    @Autowired
    private ClassesDao classesDao;

    @Test
    @Transactional
    @Rollback(true)
    public void test(){
//        cstDao.searchByCourseScheduleId();
    }


}
