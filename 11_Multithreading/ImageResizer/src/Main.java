import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static int newWidth = 300;
    public static String srcFolder = "data/src";
    public static String dstFolder = "data/dst";
    public static int cores = Runtime.getRuntime().availableProcessors();
    static Queue<File> filesQueue = new ConcurrentLinkedQueue<File>();

    public static void main(String[] args) {
        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        filesQueue.addAll(Arrays.asList(Objects.requireNonNull(srcDir.listFiles())));

        System.out.println("CPU cores: " + cores);

        //Обычное уменьшение изображения
//        for (int i = 0; i < cores; i++) {
//            new Thread(new ImageResizer(filesQueue, newWidth, dstFolder, start)).start();
//        }

        //Качественное уменьшение изображения

        for (int i = 0; i < cores; i++) {
            new Thread(new SuperImageResizer(filesQueue, newWidth, dstFolder, start)).start();
        }
    }
}