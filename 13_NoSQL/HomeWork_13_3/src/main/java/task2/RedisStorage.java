package task2;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.Map;

public class RedisStorage
{
    private static final String DOCKER_ADDRESS = "redis://127.0.0.1:6379";
    private RedissonClient redissonClient;
    private RMap<String, Map<String, Integer>> studentsMap;

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress(DOCKER_ADDRESS);
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Failed to connect to Redis");
            System.out.println(Exc.getMessage());
        }

        studentsMap = redissonClient.getMap("students");
    }

    public void initData() {
        Map<String, Integer> courses = new HashMap<>();
        courses.put("Web-developer", 1);
        courses.put("Data Science", 4);
        studentsMap.fastPut("Ivanov I.I.", courses);
    }

    public void hGetAll() {
        studentsMap.readAllEntrySet().forEach(System.out::println);
    }

    public boolean increment(String studentFio, String course, int inc) {
        if (!studentsMap.containsKey(studentFio) || inc <= 0) {
            return false;
        }
        Map<String, Integer> studentCoursesMap = studentsMap.get(studentFio);
        if (!studentCoursesMap.containsKey(course)) {
            return false;
        }
        studentCoursesMap.put(course, studentCoursesMap.get(course) + inc);
        studentsMap.fastPutAsync(studentFio, studentCoursesMap);
        return true;
    }
}
