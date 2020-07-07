package main.controllers;

import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ModelAndView userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("usersCount", userService.allUsers().size());

        return new ModelAndView("admin");
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity getUser(@PathVariable Long id, Model model) {
        if (!userService.getUser(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(userService.getUser(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(userService.getUser(id).get(), HttpStatus.OK);
    }
}
