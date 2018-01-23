package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.CoursePO;
import com.yejunfeng.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public CoursePO searchById(String courseId) {
        return hibernateTemplate.get(CoursePO.class,courseId);
    }
}
