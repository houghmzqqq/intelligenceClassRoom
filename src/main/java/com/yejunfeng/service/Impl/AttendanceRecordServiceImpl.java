package com.yejunfeng.service.Impl;

import com.yejunfeng.PO.AttendanceRecordPO;
import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.dao.AttendanceRecordDao;
import com.yejunfeng.dao.ClassroomDao;
import com.yejunfeng.dao.CourseScheduleDao;
import com.yejunfeng.dao.StudentDao;
import com.yejunfeng.exception.RecordSaveException;
import com.yejunfeng.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    @Autowired
    private AttendanceRecordDao ard;
    @Autowired
    private CourseScheduleDao csd;
    @Autowired
    private ClassroomDao cd;
    @Autowired
    private StudentDao studentDao;

    @Override
    public void addRecord(AttendanceRecordPO arPO) throws RecordSaveException {
        if(csd.searchCoursePlanById(arPO.getCourseSchedulePO().getCourseScheduleId()) == null ||
                cd.searchById(arPO.getClassroomPO().getRoomId()) == null ||
                studentDao.searchById(arPO.getStudentPO().getStudentId()) == null){
            throw new RecordSaveException();
        }
        ard.addAttenRecord(arPO);
    }

    @Override
    public DividePageVO getStuRecordDivide(int thisPage, int rowOfEachPage) {
        return ard.searchByStuId(thisPage,rowOfEachPage);
    }

    @Override
    public DividePageVO getStuRecordDivide(int thisPage, int rowOfEachPage, String stuName) {

        if(stuName==null || stuName.equals("no")){
            return getStuRecordDivide(thisPage, rowOfEachPage);
        }else {
            return ard.searchByStuId(thisPage,rowOfEachPage,stuName);
        }
    }
}
