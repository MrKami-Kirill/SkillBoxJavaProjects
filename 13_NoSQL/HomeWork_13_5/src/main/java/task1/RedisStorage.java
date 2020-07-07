package task1;

import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RedisStorage
{
    private final static List<String> USER_TOWNS_LIST = Arrays.asList(
            "Paris",
            "Venice",
            "London",
            "New York",
            "Barcelona",
            "St. Petersburg",
            "Rome",
            "Abu Dhabi",
            "Beijing",
            "Jerusalem",
            "Tokyo");
    private final Random flightTicketPriceGenerator = new Random();
    private RedissonClient redissonClient;
    private RScoredSortedSet<String> towns;

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Failed to connect to Redis");
            System.out.println(Exc.getMessage());
        }
        towns = redissonClient.getScoredSortedSet("towns");
    }

    public void initData() {
        USER_TOWNS_LIST.forEach(userTown -> towns.add(flightTicketPriceGenerator.nextInt(50000), userTown));
    }

    public void getTicketPriceAll() {
        System.out.println("All ticket prices for towns:");
        towns.readAll().forEach(town -> System.out.println("City: " + town + " - Ticket price: " + towns.getScore(town) + " RUB"));
    }

    public void showMostCheapestFlights(int amount) {
        if (amount > 0 && amount < towns.size()) {
            System.out.println("\n" + amount + " cheapest flights:");
            towns.readAll().stream()
                    .sorted(Comparator.comparing(town -> towns.getScore(town)))
                    .limit(amount)
                    .forEach(town -> System.out.println("City: " + town + " - Ticket price: " + towns.getScore(town) + " RUB"));
        } else {
            System.out.println("Invalid value");
        }
    }

    public void showMostExpensiveFlights(int amount) {
        if (amount > 0 && amount < towns.size()) {
            System.out.println("\n" + amount + " most expensive flights:");
            towns.readAll().stream()
                    .sorted((town1, town2) -> towns.getScore(town2).compareTo(towns.getScore(town1)))
                    .limit(amount)
                    .forEach(town -> System.out.println("City: " + town + " - Ticket price: " + towns.getScore(town) + " RUB"));
        } else {
            System.out.println("Invalid value");
        }
    }
}
