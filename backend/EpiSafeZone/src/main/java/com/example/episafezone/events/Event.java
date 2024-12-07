package com.example.episafezone.events;

import java.time.LocalTime;

public abstract class Event {
    private String title;
    private String description;
    private LocalTime time;

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
        this.time = LocalTime.now();
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
}
