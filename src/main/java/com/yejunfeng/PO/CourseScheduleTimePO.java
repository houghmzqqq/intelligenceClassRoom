package com.yejunfeng.PO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="schedule_time")
public class CourseScheduleTimePO {

    @Id @Column(name="sch_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleTimeId;

    @Column(name="day_of_week")
    private int dayOfWeek;
    @Column(name="start_week")
    private int startWeek;
    @Column(name="end_week")
    private int endWeek;
    @Column(name="start_lesson")
    private int startLesson;
    @Column(name="end_lesson")
    private int endLesson;
    @Column(name="mode")
    private int mode;

    @ManyToOne(targetEntity = ClassroomPO.class)
    @JoinColumn(name="room_id")
    private ClassroomPO classroomPO;

    @ManyToOne(targetEntity = CourseSchedulePO.class)
    @JoinColumn(name="course_sch_id")
    private CourseSchedulePO courseSchedulePO;

    public CourseScheduleTimePO() {
    }

    public CourseScheduleTimePO(int scheduleTimeId, int dayOfWeek, int startWeek, int endWeek, int startLesson, int endLesson, int mode) {
        this.scheduleTimeId = scheduleTimeId;
        this.dayOfWeek = dayOfWeek;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startLesson = startLesson;
        this.endLesson = endLesson;
        this.mode = mode;
    }

    public int getScheduleTimeId() {
        return scheduleTimeId;
    }

    public void setScheduleTimeId(int scheduleTimeId) {
        this.scheduleTimeId = scheduleTimeId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(int startLesson) {
        this.startLesson = startLesson;
    }

    public int getEndLesson() {
        return endLesson;
    }

    public void setEndLesson(int endLesson) {
        this.endLesson = endLesson;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public CourseSchedulePO getCourseSchedulePO() {
        return courseSchedulePO;
    }

    public void setCourseSchedulePO(CourseSchedulePO courseSchedulePO) {
        this.courseSchedulePO = courseSchedulePO;
    }

    public ClassroomPO getClassroomPO() {
        return classroomPO;
    }

    public void setClassroomPO(ClassroomPO classroomPO) {
        this.classroomPO = classroomPO;
    }
}
