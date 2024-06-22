package com.luv2code.cruddemo.entity.generated_using_intellij;

import jakarta.persistence.*;

//@Entity
@Table(name = "course_student", schema = "hb-05-many-to-many", indexes = {
        @Index(name = "FK_STUDENT_idx", columnList = "student_id")
})
public class CourseStudent {
    @EmbeddedId
    private CourseStudentId id;

    @MapsId("courseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public CourseStudentId getId() {
        return id;
    }

    public void setId(CourseStudentId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}