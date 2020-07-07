package task1;

public class RunRedis
{
    public static void main(String[] args) {
        RedisStorage rs = new RedisStorage();
        rs.init();
        rs.initData();
        rs.getTicketPriceAll();
        rs.showMostCheapestFlights(3);
        rs.showMostExpensiveFlights(3);
    }
}
