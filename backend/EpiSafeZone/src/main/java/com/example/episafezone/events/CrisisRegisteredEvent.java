package com.example.episafezone.events;

public class CrisisRegisteredEvent extends Event{
    private EventsType type;
    public CrisisRegisteredEvent(){
        super("Se ha registrado una crisis.");
        this.type = EventsType.CRISIS;
    }

    public EventsType getType() {
        return type;
    }
}
