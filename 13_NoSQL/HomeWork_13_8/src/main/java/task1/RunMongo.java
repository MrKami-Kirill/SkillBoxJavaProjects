package task1;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class RunMongo
{
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> booksCollection = database.getCollection("books");
        booksCollection.drop();

        Stream<Book> booksStream = Stream.of(
                new Book("Мастер и Маргарита", "Михаил Булгаков", 1940),
                new Book("Собачье сердце", "Михаил Булгаков", 1925),
                new Book("Преступление и наказание", "Федор Достоевский", 1866),
                new Book("Мёртвые души", "Николай Гоголь", 1842),
                new Book("Война и мир", "Лев Толстой", 1868)
        );

        booksStream.forEach(book -> {
            booksCollection.insertOne(
                    new Document()
                            .append("name", book.getName())
                            .append("author", book.getAuthor())
                            .append("year", book.getYear())
            );
        });

        System.out.println("Самая старая книга:");
        System.out.println(booksCollection.find().sort(BsonDocument.parse("{year: 1}")).first());
        System.out.println("Книги любимого автора:");
        booksCollection.find(Filters.eq("author", "Михаил Булгаков"))
                .forEach((Consumer<Document>) System.out::println);
    }
}
