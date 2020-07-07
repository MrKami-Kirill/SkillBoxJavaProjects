package task2.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private Integer age;
    private String courses;
    private List<Course> courseList = new ArrayList<>();

    public Document createDocument() {
        Document document = new Document();
        document.put("Name", this.name);
        document.put("Age", this.age);
        document.put("Courses", this.courseList.stream()
                .map(course -> new Document().append("course:", course.getName())).collect(Collectors.toList()));
        return document;
    }
}
