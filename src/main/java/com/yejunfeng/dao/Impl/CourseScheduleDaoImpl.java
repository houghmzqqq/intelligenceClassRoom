package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.CourseSchedulePO;
import com.yejunfeng.dao.CourseScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseScheduleDaoImpl implements CourseScheduleDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void addCoursePlan(CourseSchedulePO courseSchedulePO) {
        hibernateTemplate.save(courseSchedulePO);
    }

    public void deleteCoursePlan(String courseScheduleId) {
        hibernateTemplate.delete(searchCoursePlanById(courseScheduleId));
    }

    public void updateCoursePlan(CourseSchedulePO courseSchedulePO) {
        hibernateTemplate.saveOrUpdate(courseSchedulePO);
    }

    public List<CourseSchedulePO> searchAllCoursePlan() {
        return hibernateTemplate.loadAll(CourseSchedulePO.class);
    }

    public CourseSchedulePO searchCoursePlanById(String courseScheduleId) {
        return hibernateTemplate.get(CourseSchedulePO.class,courseScheduleId);
    }
}
