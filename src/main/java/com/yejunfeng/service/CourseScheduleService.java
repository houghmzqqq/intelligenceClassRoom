package com.yejunfeng.service;

import com.yejunfeng.PO.ClassroomPO;
import com.yejunfeng.VO.CourseScheduleVO;
import com.yejunfeng.util.CourseSchedule;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CourseScheduleService {
    /**
     * 将课表计划存入数据库中
     * @param couStr 课程时间安排表集合
     */
    public void saveCourseSchedule(List<CourseSchedule> courseSchedules) throws Exception;

    /**
     * 查找全部教室
     * @return 教室实例的list集合
     */
    public List<ClassroomPO> findAllClassroom();

    /**
     * 查找某学年、某学期、某教室的课程安排表
     * @param roomId 课室id
     * @param range 学年
     * @param term 学期
     * @return 课表安排的list集合
     */
    public List<CourseScheduleVO> findByRoomIdForTerm(String roomId,String range,int term);

    /**
     * 向考勤机发送课程时间安排表
     * @param roomId 课室id
     * @param range 学年
     * @param term 学期
     */
    public void sendMsgToAtten(String roomId,String range,int term);

    /**
     * 从文件中读取课表数据
     * @param file 文件
     */
    public void readCouSchFromFile(MultipartFile file) throws Exception;
}
