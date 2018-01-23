package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.TeacherPO;
import com.yejunfeng.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public TeacherPO searchById(String teacherId) {
        return hibernateTemplate.get(TeacherPO.class,teacherId);
    }
}
