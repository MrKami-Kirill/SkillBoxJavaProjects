import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println("Все самолеты в аэропорту - " + airport.getAllAircrafts());
        System.out.println("Число самолетов в аэропорту = " + airport.getAllAircrafts().size());

    }
}
