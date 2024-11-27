package com.example.episafezone.events;

public class TimeExcidedEvent extends Event {
    private EventsType type;
    public TimeExcidedEvent() {
        super("Han pasado más de 5 minutos desde que se ha iniciado la crisis. Aplica la  medicina " +
                "de emergencia");
        this.type = EventsType.CRISIS ;
    }
}
