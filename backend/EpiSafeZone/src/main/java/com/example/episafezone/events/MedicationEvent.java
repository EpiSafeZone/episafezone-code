package com.example.episafezone.events;

public class MedicationEvent extends Event {
    public MedicationEvent(String title,String description){
        super(title,description);
    }

    public static class MedicationTakenEvent extends MedicationEvent{
        public MedicationTakenEvent() {
            super("MEDICINA TOMADA","Medicacion tomada");
        }
    }

    public static class MedicationHasToBeTakenEvent extends MedicationEvent{
        public MedicationHasToBeTakenEvent() {
            super("HAY QUE TOMAR LA MEDICACION","Acuerdese de tomarse la medicacion");
        }
    }
}
