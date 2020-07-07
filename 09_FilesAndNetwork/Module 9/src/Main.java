import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Домашнее задание 9.1
//        try {
//            long dirSize = Files.walk(Paths.get("C:\\Users\\Кирилл\\Downloads"))
//                    .map(Path::toFile)
//                    .filter(File::isFile)
//                    .mapToLong(File::length)
//                    .sum();
//            System.out.println(dirSize + " bytes");
//            System.out.println((dirSize / Math.pow(2, 10)) + " kilobytes");
//            System.out.println((dirSize / Math.pow(2, 20)) + " megabytes");
//            System.out.println((dirSize / Math.pow(2, 30)) + " gigabytes");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//    }

        //Домашнее задание 9.2 (toBe)
        try {
            ArrayList<String> selectFiles = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                selectFiles.add(Integer.toString(i));
            }
            Files.write(Paths.get("data/file1.txt"), selectFiles);

            Path pathSource = Paths.get("data/file1.txt");
            Path pathDestination = Paths.get("data2/file1.txt");

            Files.copy(pathSource, pathDestination);
            System.out.println("Source file copied successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
