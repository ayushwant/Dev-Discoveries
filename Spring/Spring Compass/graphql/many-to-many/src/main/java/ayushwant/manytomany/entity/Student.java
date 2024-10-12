package ayushwant.manytomany.entity;

import ayushwant.manytomany.repository.StudentRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student", schema = "hb-05-many-to-many")
public class Student {
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

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new LinkedHashSet<>();

}