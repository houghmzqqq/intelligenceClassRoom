package com.yejunfeng.PO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="course")
public class CoursePO {
    @Id
    @Column(name="course_id")
    private String courseId;
    @Column(name="course_name")
    private String courseName;

    public CoursePO() {
    }

    public CoursePO(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
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

}
