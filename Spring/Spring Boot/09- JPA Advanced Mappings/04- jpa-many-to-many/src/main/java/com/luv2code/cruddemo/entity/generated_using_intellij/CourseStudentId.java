package com.luv2code.cruddemo.entity.generated_using_intellij;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

//@Embeddable
public class CourseStudentId implements java.io.Serializable {
    private static final long serialVersionUID = -3894967101603301549L;
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CourseStudentId entity = (CourseStudentId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.courseId, entity.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

}