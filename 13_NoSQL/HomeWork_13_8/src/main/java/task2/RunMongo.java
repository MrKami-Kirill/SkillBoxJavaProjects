package task2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;
import task2.beans.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RunMongo
{
    public static final String pathToCsvFile = "C:/Users/Kirill/IdeaProjects/Git Start/src/java_basics/13_NoSQL/HomeWork_13_8/src/main/java/task2/data/mongo.csv";
    public static final File csvFile = new File(pathToCsvFile);

    public static void main(String[] args) throws FileNotFoundException {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> studentsCollection = database.getCollection("students");
        studentsCollection.drop();

        CsvParser csvParser = new CsvParser(csvFile);
        studentsCollection.insertMany(csvParser.createCourseList().stream()
                .map(Student::createDocument).collect(Collectors.toList())
        );

        System.out.println("Общее количество студентов в базе: " + studentsCollection.countDocuments());

        List<Document> studentslist = studentsCollection.find(Filters.gt("Age", 40)).into(new ArrayList<>());
        System.out.println("Количество студентов старше 40 лет: " + studentslist.size());

        BsonDocument nameYoungestStudent = BsonDocument.parse("{Age: 1}");
        System.out.println("Имя самого молодого студента: "
                + studentsCollection.find().sort(nameYoungestStudent).first().getString("Name"));

        BsonDocument courseListOldestStudent = BsonDocument.parse("{Age: -1}");
        System.out.println("Список курсов самого старого студента: "
                + studentsCollection.find().sort(courseListOldestStudent).first().get("Courses"));


    }
}
