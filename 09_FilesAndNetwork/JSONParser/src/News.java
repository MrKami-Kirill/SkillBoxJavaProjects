import lombok.Data;

@Data

public class News
{
    public static String topic;
    public static String news;

    public News (String name, String link) {
        this.topic = name;
        this.news = link;
    }
}
