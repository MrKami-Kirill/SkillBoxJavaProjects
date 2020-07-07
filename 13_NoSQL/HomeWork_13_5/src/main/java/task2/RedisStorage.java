package task2;

import org.redisson.Redisson;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class RedisStorage
{
    public static final int USERS_AMOUNT = 20;

    private RedissonClient redissonClient;
    private RDeque<Integer> registeredUsersIds;

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Failed to connect to Redis");
            System.out.println(Exc.getMessage());
        }
        registeredUsersIds = redissonClient.getDeque("users");
    }

    public void initData() {
        for (int i = 0; i < USERS_AMOUNT; i++) {
            registeredUsersIds.add(i);
        }
    }

    /**
     * @param userId
     * Добавляет пользователя с userId в конец очереди
     */
    public void addLast(int userId) {
        registeredUsersIds.addLast(userId);
    }

    /**
     * @param userId
     * Добавляет пользователя с userId в начало очереди
     */
    public void pushUser(int userId) {
        registeredUsersIds.push(userId);
    }

    /**
     * Возвращает ID первого пользователя в очереди
     * @return - ID пользователя
     */
    public Integer peekFirstUser() {
        return registeredUsersIds.peekFirst();
    }

    /**
     * Возвращает ID первого пользователя в очереди и удаляет его из очереди
     * @return - ID пользователя
     */
    public Integer removeFirstUser() {
        return registeredUsersIds.removeFirst();
    }
}
