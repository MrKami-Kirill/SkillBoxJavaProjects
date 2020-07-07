package main.rest.service;

import javassist.NotFoundException;
import main.rest.model.Task;
import main.rest.model.TaskStatus;
import main.rest.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTaskList() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task: taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    public void createTask(Task task) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date taskDate = format.parse(task.getDate());
        if (taskDate.before(new Date())) {
            task.setStatus(TaskStatus.EXPIRED);
        } else {
            task.setStatus(TaskStatus.CREATED);
        }
        taskRepository.save(task);
    }

    public void markInProgress(Integer id) throws NotFoundException{
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.IN_PROGRESS);
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

    public Optional getDeleteTask(Integer id) {
        return taskRepository.findById(id);
    }

    public void markExpired(Integer id) throws ParseException, NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date taskDate = format.parse(task.getDate());
        if (optionalTask.isPresent()) {
            task.setStatus(TaskStatus.EXPIRED);
            taskRepository.save(task);
        }
        if (taskDate.before(new Date())) {
            task.setStatus(TaskStatus.EXPIRED);
            taskRepository.save(task);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }

    }

    public void deleteTask(Integer id) throws NotFoundException {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new NotFoundException(Integer.toString(id));
        }
    }
}
