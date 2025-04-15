package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GreetingService {
    public String getSimpleGreeting(){
        return "hello buddy ";
    }
    private static final Map<Long, GreetingEntity> greetingStore = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong();
    public static String getPersonalGreeting(String name, String lastName) {
        if (name != null && !name.isBlank() && lastName != null && !lastName.isBlank()) {
            return "Hello " + name + " " + lastName;
        } else if (name != null && !name.isBlank()) {
            return "Hello " + name;
        } else if (lastName != null && !lastName.isBlank()) {
            return "Hello " + lastName;
        } else {
            return "Hello World";
        }
    }

    public static GreetingEntity saveGreeting(String name, String message) {
        Long id = idGenerator.incrementAndGet();
        GreetingEntity entity = new GreetingEntity(id, name, message);
        greetingStore.put(id, entity);
        return entity;
    }



    public static List<GreetingEntity> getAllGreetings() {
            return new ArrayList<>(greetingStore.values());
        }

}