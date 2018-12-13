package com.example.amir.examtestapp;

import java.io.Serializable;

public class Persion implements Serializable {
    private String firstName;

    public Persion(String firstName) {
        this.firstName = firstName;
    }


    public String getFirstName() {
        return firstName;
    }

}
