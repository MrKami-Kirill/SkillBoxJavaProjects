package main.rest.controllers;

import main.rest.model.Task;
import main.rest.service.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class TaskController {

    @Autowired
    private TaskServiceImp taskStoreService;

    @GetMapping("/list/")

    public ResponseEntity<Object> getTaskList(Model model) {
        return new ResponseEntity<>(taskStoreService.getTaskList(), HttpStatus.OK);
    }

    @PostMapping("/list/")
    public ResponseEntity<Object> createTask(Task task) throws ParseException {
        taskStoreService.createTask(task);
        return new ResponseEntity<>("Task is created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/listInProgress/{id}")
    public ResponseEntity<Object> markInProgress(@PathVariable Integer id) {
        taskStoreService.markInProgress(id);
        return new ResponseEntity<>("The Task successfully updated the status to <In-Progress>", HttpStatus.OK);
    }

    @PostMapping("/listCanceled/{id}")
    public ResponseEntity<Object> markCanceled(@PathVariable Integer id) {
        taskStoreService.markCanceled(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Canceled>", HttpStatus.OK);
    }

    @PostMapping("/listClosed/{id}")
    public ResponseEntity<Object> markClosed(@PathVariable Integer id) {
        taskStoreService.markClosed(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Closed>", HttpStatus.OK);
    }

    @PostMapping("/listCompleted/{id}")
    public ResponseEntity<Object> markCompleted(@PathVariable Integer id) {
        taskStoreService.markCompleted(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Completed>", HttpStatus.OK);
    }

    @PostMapping("/listDeleted/{id}")
    public ResponseEntity<Object> markDeleted(@PathVariable Integer id) {
        taskStoreService.markDeleted(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Deleted>", HttpStatus.OK);
    }

    @PostMapping("/listExpired/{id}")
    public ResponseEntity<Object> markExpired(@PathVariable Integer id) {
        taskStoreService.markExpired(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Expired>", HttpStatus.OK);
    }

    @PostMapping("/list/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Integer id) {
        taskStoreService.deleteTask(id);
        return new ResponseEntity<>("Task is deleted successsfully", HttpStatus.OK);
    }
}
