package main.service;

import javassist.NotFoundException;
import main.model.Task;
import main.model.TaskStatus;
import main.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTaskList() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task task: taskIterable) {
            tasks.add(task);
            LocalDateTime localDateTimeTask = LocalDateTime.parse(task.getDate());
            LocalDateTime now = LocalDateTime.now().withNano(0).withSecond(0);
            if ((localDateTimeTask.isBefore(now)) && ((task.getStatus() == TaskStatus.CREATED) || (task.getStatus() == TaskStatus.IN_PROGRESS))) {
                task.setStatus(TaskStatus.EXPIRED);
                taskRepository.save(task);
            }
        }
        return tasks;
    }

    public void createTask(Task task) throws NullPointerException {
        if ((task.getName().length() > 0) && (!task.getDate().isEmpty() && (task.getDescription().length() > 0))) {
            LocalDateTime localDateTimeTask = LocalDateTime.parse(task.getDate());
            LocalDateTime now = LocalDateTime.now().withNano(0).withSecond(0);
            if (localDateTimeTask.isBefore(now)) {
                task.setStatus(TaskStatus.EXPIRED);
            } else {
                task.setStatus(TaskStatus.CREATED);
            }
            taskRepository.save(task);
        } else {
            throw new NullPointerException(task.toString());
        }
    }

    public void deleteTask(Integer id) throws NotFoundException {
        if (taskRepository.findById(id).isPresent()) {
            System.out.println("Method deleteTask called");
            taskRepository.deleteById(id);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }

    public void markInProgress(Integer id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        LocalDateTime localDateTimeTask = LocalDateTime.parse(optionalTask.get().getDate());
        LocalDateTime now = LocalDateTime.now().withNano(0).withSecond(0);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (localDateTimeTask.isBefore(now)) {
                task.setStatus(TaskStatus.EXPIRED);
            } else {
                task.setStatus(TaskStatus.IN_PROGRESS);
            }
            taskRepository.save(task);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }

    public void markCanceled(Integer id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.CANCELED);
            taskRepository.save(task);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }

    public void markClosed(Integer id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.CLOSED);
            taskRepository.save(task);
        } throw new NotFoundException(Integer.toString(id));
    }

    public void markCompleted(Integer id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.COMPLETED);
            taskRepository.save(task);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }

    public void markDeleted(Integer id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.DELETED);
            taskRepository.save(task);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }

    public void markExpired(Integer id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.EXPIRED);
            taskRepository.save(task);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }
}
