package com.yejunfeng.service;

import com.yejunfeng.PO.AttendanceRecordPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.exception.RecordSaveException;

public interface AttendanceRecordService {

    /**
     * 添加考勤记录
     * @param arPO 考勤记录实例
     */
    public void addRecord(AttendanceRecordPO arPO) throws RecordSaveException;

    /**
     * 分页查询全部学生的考勤记录
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @return 分页信息
     */
    public DividePageVO getStuRecordDivide(int thisPage, int rowOfEachPage);

    /**
     * 分页查找某个学生的考勤记录
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @param stuName 学生姓名
     * @return
     */
    public DividePageVO getStuRecordDivide(int thisPage, int rowOfEachPage,String stuName);


}
