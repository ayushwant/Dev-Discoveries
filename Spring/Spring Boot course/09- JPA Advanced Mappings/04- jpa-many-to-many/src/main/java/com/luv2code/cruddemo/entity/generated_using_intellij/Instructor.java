package com.luv2code.cruddemo.entity.generated_using_intellij;

import jakarta.persistence.*;

//@Entity
@Table(name = "instructor", schema = "hb-05-many-to-many", indexes = {
        @Index(name = "FK_DETAIL_idx", columnList = "instructor_detail_id")
})
public class Instructor {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "email", length = 45)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

}