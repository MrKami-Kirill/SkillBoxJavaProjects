import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data

@Entity
@Table(name = "Teachers")
public class Teacher
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    private int age;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> courseList;

    @Override
    public String toString() {
        return "Teacher{" +
                "id= " + id +
                ", name= " + name +
                ", salary= " + salary +
                ", age= " + age +
                "}\n";
    }

    public Teacher() {
    }
}
