package task1;

import org.redisson.Redisson;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class RedisStorage
{
    private RedissonClient redissonClient;
    private RSet<String> todoSet;

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Failed to connect to Redis");
            System.out.println(Exc.getMessage());
        }
        todoSet = redissonClient.getSet("todoSet");
    }

    public void addNewTodo(String todo) {
        todoSet.add(todo);
        System.out.println("Added new todo: " + todo);
        System.out.println("todoSet size:" + todoSet.size());
    }

    public void doTodo(String todo) {
        todoSet.remove(todo);
        System.out.println(todo + " -> done 100%.");
        System.out.println("todoSet size:" + todoSet.size());
    }
}
