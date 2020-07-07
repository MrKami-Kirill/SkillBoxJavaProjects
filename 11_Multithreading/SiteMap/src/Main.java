import java.io.FileOutputStream;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;


public class Main {

    public static final String LINK = "https://lenta.ru/";

    public static void main(String[] args) throws Exception {
        SiteMapNode sitemapRoot = new SiteMapNode(LINK);
        new ForkJoinPool().invoke(new SiteMapRecursiveAction(sitemapRoot));


        FileOutputStream stream = new FileOutputStream("data/siteMap.txt");
        String result = createSiteMap(sitemapRoot, 0);
        stream.write(result.getBytes());
        stream.flush();
        stream.close();
    }


    public static String createSiteMap(SiteMapNode node, int depth) {
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getUrl());
        node.getChildren().forEach(child -> {
            result.append("\n").append(createSiteMap(child, depth + 1));
        });
        return result.toString();
    }
}
