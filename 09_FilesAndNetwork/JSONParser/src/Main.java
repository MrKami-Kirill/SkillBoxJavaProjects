import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static final String LINK = "https://yandex.ru/news/";
    public static final String FILE_NAME = "data/News.json";
    public static void main(String[] args) {
        Document doc;
        try {
            doc = Jsoup.connect(LINK)
                    .userAgent("Chrome/80.0.3987.132")
                    .referrer("http://www.google.com")
                    .get();
            Map<String, List<String>> news = new TreeMap<>();
            Elements elements = doc.select("[class^=stories-set stories-set_main_no stories-set_pos_]");
            //System.out.println(elements);
            for (int i = 1; i < elements.size(); i++) {
                String str = "[class=stories-set stories-set_main_no stories-set_pos_" + i + "]";
                Elements element = doc.select(str);
                for (Element newsTopic : element.select("[class^=link link_theme_normal rubric-label rubric-label_top]")) {
                    System.out.println();
                    System.out.println("Topic: " + newsTopic.text());
                    System.out.println();
                    List<String> newsList = new ArrayList<>();
                    for (Element newsText : element.select("h2")) {
                        System.out.println("News: " + newsText.text());
                        newsList.add(newsText.text());
                    }
                    news.put(newsTopic.text(), newsList);
                }
            }
            Elements element = doc.select("[class=stories-set stories-set_main_no stories-set_pos_last]");
            for (Element newsTopic : element.select("[class^=link link_theme_normal rubric-label rubric-label_top]")) {
                System.out.println();
                System.out.println("Topic: " + newsTopic.text());
                System.out.println();
                List<String> newsList = new ArrayList<>();
                for (Element newsText : element.select("h2")) {
                    System.out.println("News: " + newsText.text());
                    newsList.add(newsText.text());
                }
                news.put(newsTopic.text(), newsList);
            }
            System.out.println(elements.size());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Files.write(Paths.get(FILE_NAME), gson.toJson(news).getBytes());

//            for (Element topicLine : doc.select("[class^=link link_theme_normal rubric-label rubric-label_top]")) {
//                System.out.println("Topic = " + topicLine.text());
//            }
//            System.out.println();
//            for (Element textLine : doc.select("[class^=link link_theme_black]")) {
//                System.out.println("News: " + textLine.text());
//            }
//            System.out.println();
//            for (Element textLine : doc.select("[class*=story__date]")) {
//                System.out.println("Source: " + textLine.text());
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public static Map<String, String> getNews(Element table) {
//        TreeMap<String, String> news = new TreeMap<>();
//        Elements rows = table.select();
//        for (int i = 1; i < rows.size(); i++) {
//
//        }
//    }


    public static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
