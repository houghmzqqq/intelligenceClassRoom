package com.yejunfeng.VO;

public class CourseScheduleVO {
    private String planId;
    private String startWeek;
    private String endWeek;
    private String squadId;
    private String time;
    private String dayOfWeek;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public String getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    public String getSquadId() {
        return squadId;
    }

    public void setSquadId(String squadId) {
        this.squadId = squadId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "CourseScheduleVO{" +
                "planId='" + planId + '\'' +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", squadId='" + squadId + '\'' +
                ", time=" + time +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
