package model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    private String name;

    public User(String name) {
        this.id = counter.incrementAndGet();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
