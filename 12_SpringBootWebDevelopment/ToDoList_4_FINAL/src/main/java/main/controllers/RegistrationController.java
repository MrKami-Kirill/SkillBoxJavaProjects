package main.controllers;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public ModelAndView registration() {
        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    public ModelAndView addUser(User user, BindingResult bindingResult, Model model) {

        LocalDate issueDocumentDate = LocalDate.parse(user.getIssueDate());
        LocalDate userBirthDate = LocalDate.parse(user.getBirthdate());

        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration");
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Password Mismatch");
            return new ModelAndView("registration");
        }

        if (!issueDocumentDate.isBefore(LocalDate.parse("1997-10-01")))
        {
            model.addAttribute("issueDocumentDateNotValid", "Passport issuance date cannot be less than 01.10.1997");
            return new ModelAndView("registration");
        }

        if ((ChronoUnit.YEARS.between(userBirthDate, issueDocumentDate) > 20) ||
                (ChronoUnit.YEARS.between(userBirthDate, issueDocumentDate) > 45))
        {
            model.addAttribute("issueDocumentDateNotValid", "Passport is invalid");
            return new ModelAndView("registration");
        }

        if(!userService.saveUser(user))
        {
            model.addAttribute("usernameError", "User Exists");
            return new ModelAndView("registration");
        }
        userService.saveUser(user);
        return new ModelAndView("redirect:/login");
    }
}
