package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.biologicznieczynny.diycosmeticsdatabase.models.AuthenticationBean;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {


    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        log.info("Authentication attempt!");
        return new AuthenticationBean("You Are authenticated!");
    }
}
