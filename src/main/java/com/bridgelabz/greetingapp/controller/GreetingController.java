package com.bridgelabz.greetingapp.controller;

import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.model.User;
import com.bridgelabz.greetingapp.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @Autowired
    private IGreetingService greetingService;

    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable("id") long id) {
        return greetingService.getGreetingById(id);
    }

    @PostMapping("")
    public Greeting greeting(
            @RequestParam(value = "firstname", defaultValue = "World") String firstname,
            @RequestParam(value = "lastname", defaultValue = "") String lastname) {

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return greetingService.addGreeting(user);
    }

    @DeleteMapping("/{id}")
    public String deleteGreetingById(@PathVariable long id) {
        greetingService.deleteGreetingById(id);
        return "Greeting with ID " + id + " has been deleted!";
    }

    @PutMapping("/{id}")
    public Greeting updateGreetingById(@PathVariable long id, @RequestBody User user) {
        return greetingService.updateGreetingById(id, user) ;
    }
}
