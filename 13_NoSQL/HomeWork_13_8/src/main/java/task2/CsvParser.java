package task2;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import task2.beans.Course;
import task2.beans.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CsvParser
{
    private final File csvFile;
    private final CsvToBean csv = new CsvToBean();

    private ColumnPositionMappingStrategy setColumMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Student.class);
        String[] columns = new String[] {"name", "age", "courses"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

    public List<Student> createCourseList() throws FileNotFoundException {
        CSVReader csvReader = new CSVReader(new FileReader(csvFile));
        List<Student> courseList = csv.parse(setColumMapping(), csvReader);
        courseList.forEach(student -> student.setCourseList(
                Arrays.stream(student.getCourses().split(",")).map(Course::new).collect(Collectors.toList()))
        );
        return courseList;
    }
}
