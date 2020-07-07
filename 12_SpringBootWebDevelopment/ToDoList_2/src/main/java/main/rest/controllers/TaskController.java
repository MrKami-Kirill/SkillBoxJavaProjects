package main.rest.controllers;

import main.rest.stor.Storage;
import main.rest.model.Task;
import main.rest.model.TaskStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class TaskController {
    @GetMapping("/list/")
    public List<Task> createdList(Model model) {
        return Storage.getTaskList();
    }

    @PostMapping("/list/")
    public int createTask(Task task) throws ParseException {
        return Storage.createTask(task);
    }

    @PostMapping("/listInProgress/{id}")
    public void markInProgress(@PathVariable Integer id) {
        Storage.setTaskStatus(id, TaskStatus.IN_PROGRESS);
    }

    @PostMapping("/listCanceled/{id}")
    public void markCanceled(@PathVariable Integer id) {
        Storage.setTaskStatus(id, TaskStatus.CANCELED);
    }

    @PostMapping("/listClosed/{id}")
    public void markClosed(@PathVariable Integer id) {
        Storage.setTaskStatus(id, TaskStatus.CLOSED);
    }

    @PostMapping("/listCompleted/{id}")
    public void markCompleted(@PathVariable Integer id) {
        Storage.setTaskStatus(id, TaskStatus.COMPLETED);
    }

    @PostMapping("/listDeleted/{id}")
    public void markDeleted(@PathVariable Integer id) {
        Storage.setTaskStatus(id, TaskStatus.DELETED);
    }

    @PostMapping("/listExpired/{id}")
    public void markExpired(@PathVariable Integer id) {
        Storage.setTaskStatus(id, TaskStatus.EXPIRED);
    }

    @PostMapping("/list/{id}")
    public void deleteTask(@PathVariable Integer id) {
        Storage.deleteTaskForeverAndEver(id);
    }


}
