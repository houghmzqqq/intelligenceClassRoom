package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.AttendanceMachinePO;
import com.yejunfeng.dao.AttendanceMachineDao;
import com.yejunfeng.exception.AttenMachException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceMachineDaoImpl implements AttendanceMachineDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void addMachine(AttendanceMachinePO am) throws AttenMachException {
        if(hibernateTemplate.get(AttendanceMachinePO.class,am.getAttendanceMachineId()) != null){
            throw new AttenMachException();
        }
        hibernateTemplate.save(am);
    }

//    public AttendanceMachinePO searchById(String roomId) {
//        return hibernateTemplate.get(AttendanceMachinePO.class,roomId);

    public void deleteMachine(String amId) {
        hibernateTemplate.delete(searchById(amId));
    }

    public void updateMachine(AttendanceMachinePO am) {
        hibernateTemplate.update(am);
    }

    public AttendanceMachinePO searchById(String amId) {
        return hibernateTemplate.get(AttendanceMachinePO.class,amId);
    }

    public List<AttendanceMachinePO> searchByRoomId(String roomId) {
        String hql = "from AttendanceMachinePO am where am.classroomPO.roomId=?";
        return (List<AttendanceMachinePO>) hibernateTemplate.find(hql,roomId);
    }

    public List<AttendanceMachinePO> searchAll() {
        String hql = "from AttendanceMachinePO am";
        return (List<AttendanceMachinePO>) hibernateTemplate.find(hql);
    }


}
