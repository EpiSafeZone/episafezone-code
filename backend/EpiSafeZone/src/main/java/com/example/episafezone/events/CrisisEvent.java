package com.example.episafezone.events;

public class CrisisEvent extends Event{

    public CrisisEvent(String title,String description) {
        super(title,description);
    }

    public static class TimeExceededEvent extends CrisisEvent{
        public TimeExceededEvent(){
            super("APLICAR MEDICINA DE EMERGENCIA","Se han excedido los 5 minutos aplicar medicina de emergencia");
        }
    }

    public static class RegistredCrisisEvent extends CrisisEvent{
        public RegistredCrisisEvent() {
            super("HA OCURRIDO UNA CRISIS","Se ha registrado una crisis");
        }
    }
}
