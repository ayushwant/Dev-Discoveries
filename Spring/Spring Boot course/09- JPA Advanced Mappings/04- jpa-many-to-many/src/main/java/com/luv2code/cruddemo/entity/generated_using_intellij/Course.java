package com.luv2code.cruddemo.entity.generated_using_intellij;

import jakarta.persistence.*;

//@Entity
@Table(name = "course", schema = "hb-05-many-to-many", indexes = {
        @Index(name = "FK_INSTRUCTOR_idx", columnList = "instructor_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "TITLE_UNIQUE", columnNames = {"title"})
})
public class Course {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 128)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

}