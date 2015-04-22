package com.github.katalisthenics;

public class Name {

    private String lastName;
    private String firstName;

    public Name(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Lastname : " + lastName + " - Firstname : " + firstName;
    }
}
