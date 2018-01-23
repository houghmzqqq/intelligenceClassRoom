package com.yejunfeng.PO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="teacher")
public class TeacherPO {
    @Id @Column(name="tech_id")
    private String teacherId;
    private String title;
    @Column(name="tech_name")
    private String teacherName;
    private String department;

    public TeacherPO() {
    }

    public TeacherPO(String teacherId, String title, String teacherName, String department) {
        this.teacherId = teacherId;
        this.title = title;
        this.teacherName = teacherName;
        this.department = department;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
