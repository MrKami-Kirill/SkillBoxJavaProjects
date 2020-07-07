import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Log4j2
@Data
public class GeneratorAndWriter extends Thread
{
    private final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private int startRegionCode;
    private int finishRegionCode;
    private int threadNumber;
    private long start;

    public GeneratorAndWriter(int startRegionCode, int finishRegionCode, int threadNumber, long start) {
        this.startRegionCode = startRegionCode;
        this.finishRegionCode = finishRegionCode;
        this.threadNumber = threadNumber;
        this.start = start;
    }

    private String padNumber(int number, int numberLength)
    {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++)
        {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }

    @Override
    public void run() {
        try {
            StringBuilder filePath = new StringBuilder()
                    .append("result/")
                    .append("Regions_")
                    .append(this.startRegionCode)
                    .append("-")
                    .append(this.finishRegionCode - 1)
                    .append(".txt");
            File file = new File(filePath.toString());
            PrintWriter writer = new PrintWriter(file);
            for (int regionCode = startRegionCode; regionCode < finishRegionCode; regionCode++)
            {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++)
                {
                    for (char firstLetter : LETTERS)
                    {
                        for (char secondLetter : LETTERS)
                        {
                            for (char thirdLetter : LETTERS)
                            {
                                builder
                                        .append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(regionCode, 2))
                                        .append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString());
            }
        writer.flush();
        writer.close();
    } catch (FileNotFoundException ex) {
            log.info(ex.getMessage());
        }
        long duration = System.currentTimeMillis() - start;
        log.info(new StringBuilder()
                .append("Finished for ")
                .append(duration)
                .append(" ms.")
        );
    }
}
