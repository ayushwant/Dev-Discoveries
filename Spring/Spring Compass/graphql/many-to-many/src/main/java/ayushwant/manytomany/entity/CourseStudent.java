package ayushwant.manytomany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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

}