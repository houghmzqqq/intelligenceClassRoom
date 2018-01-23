package com.yejunfeng.util;

public class ScheduleTime {
    private int startWeek;
    private int endWeek;
    private String roomId;
    private String roomName;
    private int startLesson;
    private int endLesson;
    private int dayOfWeek;
    private int mode;

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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "ScheduleTime{" +
                "startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", startLesson=" + startLesson +
                ", endLesson=" + endLesson +
                ", dayOfWeek=" + dayOfWeek +
                ", mode=" + mode +
                '}';
    }
}
