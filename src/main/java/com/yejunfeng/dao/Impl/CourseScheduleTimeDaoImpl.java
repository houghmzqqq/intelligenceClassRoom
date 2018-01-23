package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.CourseScheduleTimePO;
import com.yejunfeng.dao.CourseScheduleTimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseScheduleTimeDaoImpl implements CourseScheduleTimeDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private List<CourseScheduleTimePO> csts ;

    public void addCourseScheduleTime(CourseScheduleTimePO cst) {
        hibernateTemplate.save(cst);
    }

    public List<CourseScheduleTimePO> searchByRoomId(String roomId) {
        return null;
    }

    public void deleteCourseScheduleTime(CourseScheduleTimePO cst) {
        hibernateTemplate.delete(cst);
    }

    public void updateCourseScheduleTime(CourseScheduleTimePO cst) {
        hibernateTemplate.saveOrUpdate(cst);
    }

    public List<CourseScheduleTimePO> searchByCourseScheduleId(String courseScheduleId) {
//        String sql = "from CourseScheduleTimePO cst where cst.dayOfWeek=?";
//        return (List<CourseScheduleTimePO>) hibernateTemplate.find(sql);
        return null;
    }

    public List<CourseScheduleTimePO> searchAll() {
//        String hql = "from CourseScheduleTimePO cst";
//        return (List<CourseScheduleTimePO>) hibernateTemplate.find(hql);
        return null;
    }

    public List<CourseScheduleTimePO> searchByDayAndClassId(String classId, int dayOfWeek) {
        return null;
    }

    public List<CourseScheduleTimePO> searchByRoomId(String roomId, String range, int term) {
        String hql = "from CourseScheduleTimePO cst where cst.classroomPO.roomId=? and cst.courseSchedulePO.range=? and cst.courseSchedulePO.term=?";
//        String hql = "from CourseScheduleTimePO cst where "
        csts = (List<CourseScheduleTimePO>) hibernateTemplate.find(hql,roomId,range,term);

        return csts;
    }
}
