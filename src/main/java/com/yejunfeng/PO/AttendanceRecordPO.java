package com.yejunfeng.PO;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="atten_record")
public class AttendanceRecordPO {

    @Id @Column(name="record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;
//    @Column(name="course_sch_id")
//    private String planId;
//    @Column(name="stu_id")
//    private String stuId;
//    @Column(name="room_id")
//    private String roomId;
    private int time;
    @Column(name = "day_of_week")
    private int dayOfWeek;
    private int week;
    private int status;
    private Timestamp attenDate;

    @ManyToOne(targetEntity = CourseSchedulePO.class)
    @JoinColumn(name="course_sch_id")
    private CourseSchedulePO courseSchedulePO;
    @ManyToOne(targetEntity = StudentPO.class)
    @JoinColumn(name = "stu_id")
    private StudentPO studentPO;
    @ManyToOne(targetEntity = ClassroomPO.class)
    @JoinColumn(name = "room_id")
    private ClassroomPO classroomPO;


    public AttendanceRecordPO() {
    }

    public AttendanceRecordPO(int time, int dayOfWeek, int week, int status, CourseSchedulePO courseSchedulePO, StudentPO studentPO, ClassroomPO classroomPO) {
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.week = week;
        this.status = status;
        this.courseSchedulePO = courseSchedulePO;
        this.studentPO = studentPO;
        this.classroomPO = classroomPO;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CourseSchedulePO getCourseSchedulePO() {
        return courseSchedulePO;
    }

    public void setCourseSchedulePO(CourseSchedulePO courseSchedulePO) {
        this.courseSchedulePO = courseSchedulePO;
    }

    public StudentPO getStudentPO() {
        return studentPO;
    }

    public void setStudentPO(StudentPO studentPO) {
        this.studentPO = studentPO;
    }

    public ClassroomPO getClassroomPO() {
        return classroomPO;
    }

    public void setClassroomPO(ClassroomPO classroomPO) {
        this.classroomPO = classroomPO;
    }

    public Timestamp getAttenDate() {
        return attenDate;
    }
    public void setAttenDate(Timestamp attenDate) {
        this.attenDate = attenDate;
    }


    @Override
    public String toString() {
        return "AttendanceRecordPO{" +
                "recordId=" + recordId +
                ", time=" + time +
                ", dayOfWeek=" + dayOfWeek +
                ", week=" + week +
                ", status=" + status +
                ", date=" + attenDate +
                ", courseSchedulePO=" + courseSchedulePO +
                ", studentPO=" + studentPO +
                ", classroomPO=" + classroomPO +
                '}';
    }
}
