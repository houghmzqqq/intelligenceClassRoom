package com.yejunfeng.dao;

import com.yejunfeng.PO.CoursePO;
import org.springframework.stereotype.Repository;

public interface CourseDao {
    /**
     * 查找单个课程
     * @param courseId 课程id
     * @return 课程实例
     */
    public CoursePO searchById(String courseId);


}
