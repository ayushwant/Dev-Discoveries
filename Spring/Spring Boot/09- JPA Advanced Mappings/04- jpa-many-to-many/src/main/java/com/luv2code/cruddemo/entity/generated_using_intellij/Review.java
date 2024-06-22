package com.luv2code.cruddemo.entity.generated_using_intellij;

import jakarta.persistence.*;

//@Entity
@Table(name = "review", schema = "hb-05-many-to-many", indexes = {
        @Index(name = "FK_COURSE_ID_idx", columnList = "course_id")
})
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "comment", length = 256)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}