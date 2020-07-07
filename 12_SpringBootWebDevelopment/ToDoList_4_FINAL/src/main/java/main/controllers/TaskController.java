package main.controllers;

import javassist.NotFoundException;
import main.model.Task;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Date;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public ModelAndView getTaskList(Model model) {
        model.addAttribute("taskCount", taskService.getTaskList().size());
        model.addAttribute("taskList", taskService.getTaskList());
        System.out.println(new Date() + ": Method getTaskList called");
        return new ModelAndView("index");
    }

    @PostMapping("/list")
    public ResponseEntity<Object> createTask(Task task) throws NullPointerException {
        taskService.createTask(task);
        System.out.println(new Date() + ": Method createTask called");
        return new ResponseEntity<>("Task created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(name = "id") int id) throws NotFoundException {
        taskService.deleteTask(id);
        System.out.println(new Date() + ": Method deleteTask called");
        return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/listInProgress/{id}")
    public ResponseEntity<Object> markInProgress(@PathVariable Integer id) throws NotFoundException {
        taskService.markInProgress(id);
        System.out.println(new Date() + ": Method markInProgress called");
        return new ResponseEntity<>("The Task successfully updated the status to <In-Progress>", HttpStatus.OK);
    }

    @PostMapping("/listCanceled/{id}")
    public ResponseEntity<Object> markCanceled(@PathVariable Integer id) throws NotFoundException {
        taskService.markCanceled(id);
        System.out.println(new Date() + ": Method markCanceled called");
        return new ResponseEntity<>("The Task successfully updated the status to <Canceled>", HttpStatus.OK);
    }

    @PostMapping("/listClosed/{id}")
    public ResponseEntity<Object> markClosed(@PathVariable Integer id) throws NotFoundException {
        taskService.markClosed(id);
        System.out.println(new Date() + ": Method markClosed called");
        return new ResponseEntity<>("The Task successfully updated the status to <Closed>", HttpStatus.OK);
    }

    @PostMapping("/listCompleted/{id}")
    public ResponseEntity<Object> markCompleted(@PathVariable Integer id) throws NotFoundException {
        taskService.markCompleted(id);
        System.out.println(new Date() + ": Method markCompleted called");
        return new ResponseEntity<>("The Task successfully updated the status to <Completed>", HttpStatus.OK);
    }

    @PostMapping("/listDeleted/{id}")
    public ResponseEntity<Object> markDeleted(@PathVariable Integer id) throws NotFoundException {
        taskService.markDeleted(id);
        System.out.println(new Date() + ": Method markDeleted called");
        return new ResponseEntity<>("The Task successfully updated the status to <Deleted>", HttpStatus.OK);
    }

    @PostMapping("/listExpired/{id}")
    public ResponseEntity<Object> markExpired(@PathVariable Integer id) throws NotFoundException, ParseException {
        taskService.markExpired(id);
        System.out.println(new Date() + ": Method markExpired called");
        return new ResponseEntity<>("The Task successfully updated the status to <Expired>", HttpStatus.OK);
    }
}
