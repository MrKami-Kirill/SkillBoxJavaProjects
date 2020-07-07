package main.rest.controllers;

import javassist.NotFoundException;
import main.rest.model.Task;
import main.rest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public ModelAndView getTaskList(Model model) {
        model.addAttribute("taskCount", taskService.getTaskList().size());
        model.addAttribute("taskList", taskService.getTaskList());
        return new ModelAndView("index");
    }

    @PostMapping("/createTask")
    public ResponseEntity<Object> createTask(Task task) throws ParseException, NullPointerException {
        if ((task.getName().length() > 0) && (!task.getDate().isEmpty() && (task.getDescription().length() > 0))) {
            taskService.createTask(task);
            return new ResponseEntity<>("Task is created successfully", HttpStatus.CREATED);
        } else {
            throw new NullPointerException(task.toString());
        }
    }

    @PostMapping("/listInProgress/{id}")
    public ResponseEntity<Object> markInProgress(@PathVariable Integer id) throws NotFoundException {
        taskService.markInProgress(id);
        return new ResponseEntity<>("The Task successfully updated the status to <In-Progress>", HttpStatus.OK);
    }

    @PostMapping("/listCanceled/{id}")
    public ResponseEntity<Object> markCanceled(@PathVariable Integer id) throws NotFoundException {
        taskService.markCanceled(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Canceled>", HttpStatus.OK);
    }

    @PostMapping("/listClosed/{id}")
    public ResponseEntity<Object> markClosed(@PathVariable Integer id) throws NotFoundException {
        taskService.markClosed(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Closed>", HttpStatus.OK);
    }

    @PostMapping("/listCompleted/{id}")
    public ResponseEntity<Object> markCompleted(@PathVariable Integer id) throws NotFoundException {
        taskService.markCompleted(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Completed>", HttpStatus.OK);
    }

    @PostMapping("/listDeleted/{id}")
    public ResponseEntity<Object> markDeleted(@PathVariable Integer id) throws NotFoundException {
        taskService.markDeleted(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Deleted>", HttpStatus.OK);
    }

    @GetMapping("/listDeleted/{id}")
    public ResponseEntity getDeleteTask(@PathVariable Integer id) {
        Optional<Task> optionalTask = taskService.getDeleteTask(id);
        if(!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @PostMapping("/listExpired/{id}")
    public ResponseEntity<Object> markExpired(@PathVariable Integer id) throws NotFoundException, ParseException {
        taskService.markExpired(id);
        return new ResponseEntity<>("The Task successfully updated the status to <Expired>", HttpStatus.OK);
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(name = "id") Integer id) throws NullPointerException, NotFoundException {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task is deleted successfully", HttpStatus.OK);
    }
}
