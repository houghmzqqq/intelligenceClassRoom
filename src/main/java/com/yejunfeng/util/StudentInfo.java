package com.yejunfeng.util;

public class StudentInfo {
    private String id;
    private String stuName;
    private String rfid;

    public StudentInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id='" + id + '\'' +
                ", stuName='" + stuName + '\'' +
                ", rfid='" + rfid + '\'' +
                '}';
    }
}
