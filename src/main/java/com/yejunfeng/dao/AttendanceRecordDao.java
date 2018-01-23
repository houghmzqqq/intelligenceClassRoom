package com.yejunfeng.dao;

import com.yejunfeng.PO.AttendanceRecordPO;
import com.yejunfeng.VO.DividePageVO;

import java.util.List;

public interface AttendanceRecordDao {
    /**
     * 增加考勤记录
     * @param arPO 考勤记录
     */
    public void addAttenRecord(AttendanceRecordPO arPO);

    /**
     * 删除考勤记录
     * @param arId 考勤记录id
     */
    public void deleteAttenRecord(int arId);

    /**
     * 更新考勤记录
     * @param arPO 考勤记录
     */
    public void updateAttenRecord(AttendanceRecordPO arPO);

    /**
     * 查找单个考勤记录
     * @param arId 考勤记录id
     * @return 考勤记录实例
     */
    public AttendanceRecordPO searchById(int arId);

    /**
     * 分页查找某个学生考勤记录
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @param stuName 学生姓名
     * @return 分页信息
     */
    public DividePageVO searchByStuId(int thisPage, int rowOfEachPage,String stuName);

    /**
     * 分页查找学生考勤记录，查找的是全部学生，不是某个学生
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @return
     */
    public DividePageVO searchByStuId(int thisPage, int rowOfEachPage);

    /**
     * 分页查询考勤记录，按每个班级、每一节课排列出来
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @return 分页信息
     */
    public DividePageVO searchByClassName(int thisPage, int rowOfEachPage);

    /**
     * 查找一个班级的每一节课的考勤记录
     * @param thisPage
     * @param rowOfEachPage
     * @param className
     * @return
     */
    public DividePageVO searchByClassName(int thisPage, int rowOfEachPage, String className);

}
