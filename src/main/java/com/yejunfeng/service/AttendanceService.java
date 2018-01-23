package com.yejunfeng.service;

import com.yejunfeng.PO.AttendanceMachinePO;
import com.yejunfeng.exception.AttenMachException;

import java.util.List;

public interface AttendanceService {
    /**
     * 查找所有考勤机信息
     * @return 考勤机实例集合
     */
    public List<AttendanceMachinePO> findAll();

    /**
     * 查找单个考勤机
     * @param attenId 考勤机id
     * @return 考勤机实例
     */
    public AttendanceMachinePO findById(String attenId);

    /**
     * 添加考勤机
     * @param am 考勤机实例
     */
    public void addAttendanceMachine(AttendanceMachinePO am) throws AttenMachException;

    /**
     * 删除考勤机
     * @param attenId 考勤机id
     */
    public void deleteAttenMach(String attenId);

    /**
     * 更新考勤机
     * @param am 考勤机实例
     */
    public void updateAttenMach(AttendanceMachinePO am);

    /**
     * 向考勤机发送后台的url，用以向服务器发送http请求
     * @param amPO 考勤机实例
     */
    public void sendMyUrlToAttend(AttendanceMachinePO amPO);

}
