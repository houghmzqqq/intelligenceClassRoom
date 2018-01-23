package com.yejunfeng.dao;

import com.yejunfeng.PO.AttendanceMachinePO;
import com.yejunfeng.exception.AttenMachException;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AttendanceMachineDao {
    /**
     * 添加考勤机信息
     * @param am 考勤机实例
     */
    public void addMachine(AttendanceMachinePO am) throws AttenMachException;

    /**
     * 删除考勤机
     * @param amId 考勤机id
     */
    public void deleteMachine(String amId);

    /**
     * 更新考勤机
     * @param am 考勤机实例
     */
    public void updateMachine(AttendanceMachinePO am);

    /**
     * 查找单个考勤机
     * @param amId 考勤机id
     * @return 考勤机实例
     */
    public AttendanceMachinePO searchById(String amId);

    /**
     * 查找课室的考勤机
     * @param roomId 课室id
     * @return 考勤机实例的list结合
     */
    public List<AttendanceMachinePO> searchByRoomId(String roomId);

    /**
     * 查找所有考勤机
     * @return 考勤机实例的list集合
     */
    public List<AttendanceMachinePO> searchAll();

//    /**
//     * 查找单个教室
//     * @param roomId 教室id
//     * @return 教室实例
//     */
//    public AttendanceMachinePO searchById(String roomId);
}
