package com.yejunfeng.util;

import java.util.List;

public class CourseSchedule {
    private String planId;
    private String courseId;
    private String courseName;
    private String tchId;
    private String tchName;
    private String squadId;
    private String squadName;
    private List<ScheduleTime> time;

    private String range;
    private int term;

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

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTchId() {
        return tchId;
    }

    public void setTchId(String tchId) {
        this.tchId = tchId;
    }

    public String getTchName() {
        return tchName;
    }

    public void setTchName(String tchName) {
        this.tchName = tchName;
    }

    public String getSquadId() {
        return squadId;
    }

    public void setSquadId(String squadId) {
        this.squadId = squadId;
    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public List<ScheduleTime> getTime() {
        return time;
    }

    public void setTime(List<ScheduleTime> time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CourseSchedule{" +
                "planId='" + planId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", tchId='" + tchId + '\'' +
                ", tchName='" + tchName + '\'' +
                ", squadId='" + squadId + '\'' +
                ", squadName='" + squadName + '\'' +
                ", time=" + time +
                '}';
    }
}
