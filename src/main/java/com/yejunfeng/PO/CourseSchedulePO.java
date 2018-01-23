package com.yejunfeng.PO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="course_schedule")
public class CourseSchedulePO {
    @Id @Column(name="course_sch_id")
    private String courseScheduleId;

    @Column(name="range")
    private String range;
    @Column(name="term")
    private int term;

    @ManyToOne(targetEntity = ClassesPO.class)
    @JoinColumn(name="class_id")
    private ClassesPO classesPO;
    @ManyToOne(targetEntity = CoursePO.class)
    @JoinColumn(name="course_id")
    private CoursePO coursePO;
    @ManyToOne(targetEntity = TeacherPO.class)
    @JoinColumn(name="tech_id")
    private TeacherPO teacherPO;

    public CourseSchedulePO() {
    }

    public CourseSchedulePO(String planId){
        this.courseScheduleId = planId;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getCourseScheduleId() {
        return courseScheduleId;
    }

    public void setCourseScheduleId(String courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }

    public ClassesPO getClassesPO() {
        return classesPO;
    }

    public void setClassesPO(ClassesPO classesPO) {
        this.classesPO = classesPO;
    }

    public CoursePO getCoursePO() {
        return coursePO;
    }

    public void setCoursePO(CoursePO coursePO) {
        this.coursePO = coursePO;
    }

    public TeacherPO getTeacherPO() {
        return teacherPO;
    }

    public void setTeacherPO(TeacherPO teacherPO) {
        this.teacherPO = teacherPO;
    }

    @Override
    public String toString() {
        return "CourseSchedulePO{" +
                "courseScheduleId='" + courseScheduleId + '\'' +
                '}';
    }
}
