package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @RequestMapping("/")
    public Integer index() {
        int index = (int) (1000 + (Math.random() * 1000));
        return index;
    }
}
