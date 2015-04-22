package com.github.katalisthenics;

public class Client {

    private Name name;

    public Client(Name name) {
        this.name = name;
    }

    public String toString() {
        return "client : " + name.toString();
    }
}
