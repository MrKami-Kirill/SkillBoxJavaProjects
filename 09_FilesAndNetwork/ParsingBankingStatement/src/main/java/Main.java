import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static String movementDataList = "data/movementList.csv";
    private static String dateFormat = "dd.MM.yy";

    public static void main(String[] args) throws Exception {
        try {

            ArrayList<MovementList> movementList = loadMovementLListFromFile();
            Collections.sort(movementList, Comparator.comparing(MovementList::getDescriptionOperation));
//            for (MovementList list : movementList) {
//                System.out.println(list);
//            }
            System.out.println("FREE INFORMATION:");
            System.out.println("\t" + "SUM OF COMING: " + movementList.stream().mapToDouble(c -> c.getComing()).sum() + " RUR");
            System.out.println("\t" + "SUM OF CONSUMPTION: " + movementList.stream().mapToDouble(c -> c.getConsumption()).sum() + " RUR");
            System.out.println("GROUP BY: ");
            movementList.stream()
                    .filter(a -> a.getConsumption() != 0)
                    .collect(Collectors.groupingBy(
                            MovementList::getSplitDescriptionOperation,
                            Collectors.summingDouble(MovementList::getConsumption)))
                    .entrySet()
                    .forEach(System.out::println);
//            movementList.stream()
//                    .filter(a -> a.getConsumption() != 0)
//                    .map(a -> {
//                        a.getSplitDescriptionOperation();
//                        a.getConsumption();
//                        return
//                                "DESCRIPTION OPERATION: " +
//                                a.getSplitDescriptionOperation() +
//                                ", CONSUMPTION: " +
//                                a.getConsumption();
//                    })
//                    .forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    private static ArrayList<MovementList> loadMovementLListFromFile()
    {
        ArrayList<MovementList> movementLists = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(movementDataList));
            lines.remove(0);
            for(String line : lines)
            {
                String[] fragments = line.split(",");
                if(fragments.length != 8) {
                    movementLists.add(new MovementList(
                            fragments[0],
                            fragments[1],
                            fragments[2],
                            (new SimpleDateFormat(dateFormat)).parse(fragments[3]),
                            fragments[4],
                            fragments[5],
                            Double.parseDouble(fragments[6]),
                            Double.parseDouble(fragments[7].replace("\"", "") + "." +fragments[8].replace("\"", ""))));
                    continue;
                }
                movementLists.add(new MovementList(
                        fragments[0],
                        fragments[1],
                        fragments[2],
                        (new SimpleDateFormat(dateFormat)).parse(fragments[3]),
                        fragments[4],
                        fragments[5],
                        Double.parseDouble(fragments[6]),
                        Double.parseDouble(fragments[7])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return movementLists;
    }
}
