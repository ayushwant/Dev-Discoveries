package ayushwant.manytomany.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CourseStudentId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -4704054739944430182L;
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CourseStudentId entity = (CourseStudentId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.courseId, entity.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

}