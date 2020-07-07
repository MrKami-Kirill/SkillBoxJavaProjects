import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;
import static java.lang.Thread.sleep;

public class SiteMapRecursiveAction extends RecursiveAction
{
    private SiteMapNode siteMapNode;

    public SiteMapRecursiveAction (SiteMapNode siteMapNode) {
        this.siteMapNode = siteMapNode;
    }

    @Override
    protected void compute() {
        try {
            sleep(500);
            Document document = Jsoup.connect(siteMapNode.getUrl())
                    .userAgent("Chrome/80.0.3987.132")
                    .referrer("http://www.google.com")
                    .timeout(10000)
                    .get();
            Elements elements = document.select("body").select("a");
            for (Element a : elements) {
                String childrenUrl = a.absUrl("href");
                if (isCorrectUrl(childrenUrl)) {
                    childrenUrl = stripParams(childrenUrl);
                    System.out.println(childrenUrl);
                    siteMapNode.addChildren(new SiteMapNode(childrenUrl));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (SiteMapNode children : siteMapNode.getChildren()) {
            SiteMapRecursiveAction task = new SiteMapRecursiveAction(children);
            task.compute();
        }
    }

    private static String stripParams(String url) {
        return url.replaceAll("\\?.+","");
    }

    private boolean isCorrectUrl(String url) {
        Pattern root = Pattern.compile("^" + siteMapNode.getUrl());
        Pattern notFile = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)");
        Pattern notAnchor = Pattern.compile("#([\\w\\-]+)?$");

        return root.matcher(url).lookingAt()
                && !notFile.matcher(url).find()
                && !notAnchor.matcher(url).find();
    }
}
