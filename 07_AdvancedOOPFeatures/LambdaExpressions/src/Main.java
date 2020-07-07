import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) throws ParseException {
        ArrayList<Employee> staff = loadStaffFromFile();
        //ДЗ 7.1
        Collections.sort(staff, Comparator.comparing(Employee::getSalary)
                .thenComparing(Employee::getName));
        for (Employee employee : staff) {
            System.out.println(employee);
        }

        //ДЗ 7.2 (часть 1)
        LocalDate ld1 = LocalDate.of(2016, 12, 31);
        LocalDate ld2 = LocalDate.of(2018, 1, 1);
        ZoneId defaultZoneId = ZoneId.systemDefault();

        //Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse("31.12.2016");
        //Date date2 = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2018");
        staff.stream()

                .filter(e -> e.getWorkStart().after(Date.from(ld1.atStartOfDay(defaultZoneId).toInstant()))
                        &&
                        e.getWorkStart().before(Date.from(ld2.atStartOfDay(defaultZoneId).toInstant())))
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .ifPresent(System.out::println);
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}