package com.yejunfeng.PO;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="student")
public class StudentPO {
    @Id @Column(name="stu_id")
    private String studentId;
    @Column(name="stu_name")
    private String studentName;
    private String gender;
    private String RFID;
    private Blob photo;

    @ManyToOne(targetEntity = ClassesPO.class)
    @JoinColumn(name="class_id",nullable = false)
    private ClassesPO classesPO;

    public StudentPO() {
    }

    public StudentPO(String studentId) {
        this.studentId = studentId;
    }

    public StudentPO(String studentId, String studentName, String gender, ClassesPO classesPO) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.classesPO = classesPO;
    }

    public String getRFID() {
        return RFID;
    }
    public void setRFID(String RFID) {
        this.RFID = RFID;
    }
    public Blob getPhoto() {
        return photo;
    }
    public void setPhoto(Blob photo) {
        this.photo = photo;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public ClassesPO getClassesPO() {
        return classesPO;
    }
    public void setClassesPO(ClassesPO classesPO) {
        this.classesPO = classesPO;
    }

    @Override
    public String toString() {
        return "StudentPO{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", photo=" + photo +
                ", classesPO=" + classesPO +
                '}';
    }
}
