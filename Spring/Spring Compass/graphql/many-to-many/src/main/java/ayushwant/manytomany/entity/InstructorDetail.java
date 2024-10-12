package ayushwant.manytomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "instructor_detail", schema = "hb-05-many-to-many")
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "youtube_channel", length = 128)
    private String youtubeChannel;

    @Column(name = "hobby", length = 45)
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail")
    private Instructor instructor;

}