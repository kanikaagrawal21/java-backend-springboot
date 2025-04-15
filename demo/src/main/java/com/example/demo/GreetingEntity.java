package com.example.demo;

public class GreetingEntity {
    private long id;
    private String name;
    private String message;

    public GreetingEntity(long l, String name , String message){
        this.name = name ;
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
