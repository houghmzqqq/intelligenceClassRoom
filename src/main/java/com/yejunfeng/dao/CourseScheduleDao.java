package com.yejunfeng.dao;

import com.yejunfeng.PO.CourseSchedulePO;
import com.yejunfeng.PO.CourseScheduleTimePO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseScheduleDao {

    /**
     * 增加课程计划
     * @param courseSchedulePO 课表计划
     */
    public void addCoursePlan(CourseSchedulePO courseSchedulePO);

//    /**
//     * 增加课程的时间安排，多个课程的时间安排对应一个课表计划
//     * @param cst 课程时间安排
//     */
//    public void addScheduleTime(CourseScheduleTimePO cst);

    /**
     * 删除课程计划
     * @param courseScheduleId 课程计划id
     */
    public void deleteCoursePlan(String courseScheduleId);

    /**
     * 更新课程计划
     * @param courseSchedulePO 课程计划
     */
    public void updateCoursePlan(CourseSchedulePO courseSchedulePO);

    /**
     * 查找所有课程计划
     * @return  courseSchedulePO 的List集合
     */
    public List<CourseSchedulePO> searchAllCoursePlan();

    /**
     * 通过id查找课程计划
     * @param courseScheduleId
     * @return courseSchedulePO 一个课程计划实例
     */
    public CourseSchedulePO searchCoursePlanById(String courseScheduleId);

//    public List<CourseSchedulePO> searchCoursePlanBy();
}
