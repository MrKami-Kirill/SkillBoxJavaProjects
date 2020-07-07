import java.util.concurrent.CopyOnWriteArrayList;

public class SiteMapNode {
    private volatile SiteMapNode parent;
    private volatile int depth;
    private String url;
    private volatile CopyOnWriteArrayList<SiteMapNode> children;

    public SiteMapNode (String url) {
        depth = 0;
        this.url = url;
        parent = null;
        children = new CopyOnWriteArrayList<>();
    }

    private int calculateDepth() {
        int result = 0;
        if (parent == null) {
            return result;
        }
        result = 1 + parent.calculateDepth();
        return result;
    }

    public synchronized void addChildren(SiteMapNode siteMapNode) {
        SiteMapNode root = getRootElement();
        if(!root.contains(siteMapNode.getUrl())) {
            siteMapNode.setParent(this);
            children.add(siteMapNode);
        }
    }

    public CopyOnWriteArrayList<SiteMapNode> getChildren() {
        return children;
    }

    public String getUrl() {
        return url;
    }

    private boolean contains(String url) {
        if (this.url.equals(url)) {
            return true;
        }
        for (SiteMapNode child : children) {
            if(child.contains(url))
                return true;
        }
        return false;
    }

    private void setParent(SiteMapNode sitemapNode) {
        synchronized (this) {
            this.parent = sitemapNode;
            this.depth = calculateDepth();
        }
    }

    public SiteMapNode getRootElement() {
        return parent == null ? this : parent.getRootElement();
    }
}