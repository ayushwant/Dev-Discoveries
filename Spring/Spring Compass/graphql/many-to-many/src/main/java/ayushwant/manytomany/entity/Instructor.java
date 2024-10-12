package ayushwant.manytomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "instructor", schema = "hb-05-many-to-many", indexes = {
        @Index(name = "FK_DETAIL_idx", columnList = "instructor_detail_id")
})
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "email", length = 45)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses = new LinkedHashSet<>();

}