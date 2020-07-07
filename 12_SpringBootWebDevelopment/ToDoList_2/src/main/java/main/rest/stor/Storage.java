package main.rest.stor;

import main.rest.model.Task;
import main.rest.model.TaskStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();
    private static AtomicInteger currentId = new AtomicInteger(0);

    public static int createTask(Task task) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date taskDate = format.parse(task.getDate());
        if (taskDate.before(new Date())) {
            task.setStatus(TaskStatus.EXPIRED);
        } else {
            task.setStatus(TaskStatus.CREATED);
        }
        task.setId(currentId.incrementAndGet());
        tasks.putIfAbsent(currentId.get(), task);
        return currentId.get();
    }

    public static ArrayList<Task> getTaskList() {
        return new ArrayList<>(tasks.values());
    }

    public static void setTaskStatus(Integer id, TaskStatus status) {
        if (id != null) {
            Task t = tasks.get(id);
            if (t != null) {
                t.setStatus(status);
            }
        }
    }

    public static void deleteTaskForeverAndEver(Integer id) {
        if (id != null) {
            tasks.remove(id);
        }
    }
}
