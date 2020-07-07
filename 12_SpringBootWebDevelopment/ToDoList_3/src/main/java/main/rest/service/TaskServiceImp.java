package main.rest.service;

import main.rest.model.Task;
import main.rest.model.TaskStatus;
import main.rest.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTaskList() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task: taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public int createTask(Task task) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date taskDate = format.parse(task.getDate());
        Task newTask = taskRepository.save(task);
        if (taskDate.before(new Date())) {
            task.setStatus(TaskStatus.EXPIRED);
            taskRepository.save(task);
        } else {
            task.setStatus(TaskStatus.CREATED);
            taskRepository.save(task);
        }
        return newTask.getId();
    }

    @Override
    public void markInProgress(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.IN_PROGRESS);
            taskRepository.save(task);
        }
    }

    @Override
    public void markCanceled(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.CANCELED);
            taskRepository.save(task);
        }
    }

    @Override
    public void markClosed(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.CLOSED);
            taskRepository.save(task);
        }
    }

    @Override
    public void markCompleted(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.COMPLETED);
            taskRepository.save(task);
        }
    }

    @Override
    public void markDeleted(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.DELETED);
            taskRepository.save(task);
        }
    }

    @Override
    public void markExpired(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.EXPIRED);
            taskRepository.save(task);
        }
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
