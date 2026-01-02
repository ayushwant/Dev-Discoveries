package ayushwant.manytomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course", schema = "hb-05-many-to-many", indexes = {
        @Index(name = "FK_INSTRUCTOR_idx", columnList = "instructor_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "TITLE_UNIQUE", columnNames = {"title"})
})
//@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 128)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new LinkedHashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Review> reviews = new LinkedHashSet<>();

}