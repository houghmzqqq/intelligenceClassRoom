package com.yejunfeng.PO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="classes")
public class ClassesPO {
    @Id @Column(name="class_id")
    private String classId;
    @Column(name="class_name")
    private String className;
    private String major;
    private int grade;

    public ClassesPO() {
    }

    public ClassesPO(String classId, String className, String major, int grade) {
        this.classId = classId;
        this.className = className;
        this.major = major;
        this.grade = grade;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ClassesPO{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                '}';
    }
}
