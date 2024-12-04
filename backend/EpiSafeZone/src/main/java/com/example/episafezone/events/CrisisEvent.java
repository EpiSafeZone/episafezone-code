package com.example.episafezone.events;

public class CrisisEvent extends Event{

    public CrisisEvent(String description) {
        super(description);
    }

    public static class TimeExceededEvent extends CrisisEvent{
        public TimeExceededEvent(){
            super("Se han excedido los 5 minutos aplicar medicina de emergencia");
        }
    }

    public static class RegistredCrisisEvent extends CrisisEvent{
        public RegistredCrisisEvent() {
            super("Se ha registrado una crisis");
        }
    }
}
