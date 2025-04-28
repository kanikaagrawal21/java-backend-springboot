package com.example.address_book;



import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import lombok.Data;

import lombok.NoArgsConstructor;



@Entity

@NoArgsConstructor

@Data

public class Contact {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String phone;

    private String city;



    public Contact(Long id, String name, String phone, String city) {

        this.id = id;

        this.name = name;

        this.phone = phone;

        this.city = city;

    }



    public Long getId() {

        return id;

    }



    public void setId(Long id) {

        this.id = id;

    }



    public String getName() {

        return name;

    }



    public void setName(String name) {

        this.name = name;

    }



    public String getPhone() {

        return phone;

    }



    public void setPhone(String phone) {

        this.phone = phone;

    }



    public String getCity() {

        return city;

    }



    public void setCity(String city) {

        this.city = city;

    }

}
