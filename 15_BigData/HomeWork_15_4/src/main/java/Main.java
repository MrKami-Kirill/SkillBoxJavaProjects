import java.io.IOException;

public class Main {

  private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  public static void main(String[] args) throws IOException {
    FileAccess fileAccess = new FileAccess("hdfs://5ca028a9ab40:8020");
    fileAccess.delete("/test1/");
    fileAccess.create("/test1/testFile.txt");
    fileAccess.create("/test1/testFile2.txt");
    fileAccess.append("/test1/testFile.txt", (" " + getRandomWord()));
    System.out.println(fileAccess.isDirectory("/test1")); //is Directory - true
    System.out.println(fileAccess.isDirectory("/test1/testFile.txt")); //is File - true
    System.out.println(fileAccess.isDirectory("/test/testFile.txt")); //false
    System.out.println(fileAccess.read("/test1/testFile.txt"));
    System.out.println(fileAccess.list("/test1/"));

  }

  private static String getRandomWord()
  {
    StringBuilder builder = new StringBuilder();
    int length = 2 + (int) Math.round(10 * Math.random());
    int symbolsCount = symbols.length();
    for(int i = 0; i < length; i++) {
      builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
    }
    return builder.toString();
  }
}
