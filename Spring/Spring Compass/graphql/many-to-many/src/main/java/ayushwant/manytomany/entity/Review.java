package ayushwant.manytomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review", schema = "hb-05-many-to-many", indexes = {
        @Index(name = "FK_COURSE_ID_idx", columnList = "course_id")
})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "comment", length = 256)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

}