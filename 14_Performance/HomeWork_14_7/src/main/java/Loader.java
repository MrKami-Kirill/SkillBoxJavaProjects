import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader
{
    private static final int THREADS = Runtime.getRuntime().availableProcessors();
    private static final int REGIONS = 100;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int finishRegionCode = 0;
        int startRegionCode = 1;
        int regions = REGIONS - REGIONS % THREADS;
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            finishRegionCode = regions - ((regions - finishRegionCode) - regions / THREADS);
            executorService.submit(new GeneratorAndWriter(startRegionCode, finishRegionCode, i, start));
            startRegionCode = finishRegionCode;
        }
        if (regions != REGIONS) {
            executorService.submit(new GeneratorAndWriter(regions, REGIONS, THREADS, start));
        }
        executorService.shutdown();
    }
}
