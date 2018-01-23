package com.yejunfeng.dao;

import com.yejunfeng.PO.TeacherPO;
import org.springframework.stereotype.Repository;

public interface TeacherDao {
    /**
     * 查找单个教师
     * @param teacherId 教师id
     * @return 教师实例
     */
    public TeacherPO searchById(String teacherId);
}
