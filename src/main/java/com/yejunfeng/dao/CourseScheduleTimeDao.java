package com.yejunfeng.dao;

import com.yejunfeng.PO.CourseScheduleTimePO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseScheduleTimeDao {

    /**
     * 增加课表时间安排
     * @param cst 课表时间安排
     */
    public void addCourseScheduleTime(CourseScheduleTimePO cst);

    /**
     * 删除课表时间安排
     * @param cst 课表时间安排
     */
    public void deleteCourseScheduleTime(CourseScheduleTimePO cst);

    /**
     * 更新课表时间安排
     * @param cst 课表时间安排
     */
    public void updateCourseScheduleTime(CourseScheduleTimePO cst);

    /**
     *  通过课程计划查询课表时间安排
     * @param courseScheduleId 课程计划id
     * @return 课表时间安排List集合
     */
    public List<CourseScheduleTimePO> searchByCourseScheduleId(String courseScheduleId);

    /**
     * 查找所有课表时间安排
     * @return 课表时间安排List集合
     */
    public List<CourseScheduleTimePO> searchAll();

    /**
     * 查找某个班级某一天的课表时间安排
     * @param classId 班级id
     * @param dayOfWeek 周天(星期几)
     * @return 课表时间安排List集合
     */
    public List<CourseScheduleTimePO> searchByDayAndClassId(String classId,int dayOfWeek);

    /**
     * 查找一间课室一个学期的课程时间安排
     * @param roomId 课室id
     * @param range 学年
     * @param term 学期
     * @return 课程时间安排的list集合
     */
    public List<CourseScheduleTimePO> searchByRoomId(String roomId,String range,int term);
}
