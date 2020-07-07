import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        try {
//        String htmlFile = parseFile("data/file.html");
//        //System.out.println(htmlFiles);
//        Document doc = Jsoup.parse(htmlFile);
//        Elements elements = doc.select("img.g-picture");
//        elements.forEach(element -> {
//            System.out.println(element.attr("src"));
//        });
            Document document = Jsoup.connect("https://lenta.ru/").get();
            Elements img = document.getElementsByTag("img");
            System.out.println(img.size());
            //List<String> list = new ArrayList<>();
            for (Element el : img) {
                String src = el.absUrl("src");
                System.out.println(src);
                //list.add(src);
                String[] element = src.split("\\/");
                int lastArrayelement = element.length;
                InputStream in = new URL(src).openStream();
                if (src.matches("(.*)jpg") || src.matches("(.*)png")) {
                    Files.copy(in, Paths.get("data/" + element[lastArrayelement - 1]), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(in, Paths.get("data/" + element[lastArrayelement - 1] + ".png"), StandardCopyOption.REPLACE_EXISTING);
                }
                in.close();
            }
//            for (String string : list) {
//                System.out.println(string);
//                String[] element = string.split("\\/");
//                InputStream in = new URL(string).openStream();
//                int lastArrayElement = element.length;
//                if (string.matches("(.*)jpg") || string.matches("(.*)png")) {
//                    Files.copy(in, Paths.get("data/" + element[lastArrayElement - 1]), StandardCopyOption.REPLACE_EXISTING);
//                } else {
//                    Files.copy(in, Paths.get("data/" + element[lastArrayElement - 1] + ".png"), StandardCopyOption.REPLACE_EXISTING);
//                }
//                in.close();
        } catch (ConnectException ex) {
            ex.getMessage();
        }
    }


    public static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
