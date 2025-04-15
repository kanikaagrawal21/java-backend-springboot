package com.example.demo;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    // UC1: Basic GET
    @GetMapping
    public String helloWorld() {
        return "Hello from BridgeLabz";
    }

    // UC2: GET with Query Parameter
    @GetMapping("/query")
    public String helloWithQuery(@RequestParam String name) {
        return "Hello " + name + " from BridgeLabz";
    }

    // UC3: GET with Path Variable
    @GetMapping("/param/{name}")
    public String helloWithPath(@PathVariable String name) {
        return "Hello " + name + " from BridgeLabz";
    }

    // UC4: POST with Body
    @PostMapping("/post")
    public String helloWithBody(@RequestBody NameRequest nameRequest) {
        return "Hello " + nameRequest.getname() + " " + nameRequest.getLastName() + " from BridgeLabz";
    }

    // UC5: PUT with Path Variable and Query Parameter
    @PutMapping("/put/{firstName}")
    public String helloWithPut(@PathVariable String firstName, @RequestParam String lastName) {
        return "Hello " + firstName + " " + lastName + " from BridgeLabz";
    }
    @PostMapping("/save")
    public GreetingEntity getAndSaveGreeting(@RequestBody NameRequest request) {
        String message = GreetingService.getPersonalGreeting(request.getname(), request.getLastName());
        String nameToUse = (request.getname() != null && !request.getname().isBlank()) ? request.getname() : "User";
        return GreetingService.saveGreeting(nameToUse, message);
    }

    // List all greetings
    @GetMapping("/all")
    public List<GreetingEntity> getAllGreetings() {
        return GreetingService.getAllGreetings();
    }



}

