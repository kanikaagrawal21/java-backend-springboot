package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // GET /greeting
    @GetMapping
    public Greeting getGreeting() {
        return new Greeting(greetingService.getSimpleGreeting());
    }

    // POST /greeting
    @PostMapping
    public Greeting postGreeting() {
        return new Greeting(greetingService.getSimpleGreeting());
    }

    // POST /greeting/personal
    @PostMapping("/personal")
    public Greeting getPersonalGreeting(@RequestBody NameRequest request) {
        String message = greetingService.getPersonalGreeting(request.getname(), request.getLastName());
        return new Greeting(message);
    }

    // POST /greeting/save
    @PostMapping("/save")
    public GreetingEntity getAndSaveGreeting(@RequestBody NameRequest request) {
        // For now, simulate saving using getPersonalGreeting and ID generation
        String message = greetingService.getPersonalGreeting(request.getname(), request.getLastName());

        // Simulate ID and entity (assuming you add a save method to service later)
        GreetingEntity saved = new GreetingEntity(
                System.currentTimeMillis(), // simple fake ID
                request.getname() != null ? request.getname() : "User",
                message
        );

        return saved;
    }

    // GET /greeting/all
    @GetMapping("/all")
    public List<GreetingEntity> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
}
