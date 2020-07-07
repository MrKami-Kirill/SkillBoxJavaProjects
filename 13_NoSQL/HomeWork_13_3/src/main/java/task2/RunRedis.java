package task2;

public class RunRedis
{
    public static void main(String[] args)
    {
        RedisStorage rs = new RedisStorage();
        rs.init();
        rs.initData();
        rs.hGetAll();
        if (rs.increment("Ivanov I.I.", "Data Science", 1)) {
            System.out.println("OK");
            rs.hGetAll();
        } else {
            System.out.println("System error.");
        }
    }
}
