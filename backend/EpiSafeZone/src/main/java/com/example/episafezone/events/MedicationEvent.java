package com.example.episafezone.events;

public class MedicationEvent extends Event {
    public MedicationEvent(String description){
        super(description);
    }

    public static class MedicationTakenEvent extends MedicationEvent{
        public MedicationTakenEvent() {
            super("Medicacion tomada");
        }
    }

    public static class MedicationHasToBeTakenEvent extends MedicationEvent{
        public MedicationHasToBeTakenEvent() {
            super("Acuerdese de tomarse la medicacion");
        }
    }
}
