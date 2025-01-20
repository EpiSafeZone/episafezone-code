package com.example.episafezone.events;

public class EventFactory {
    public static Event createEvent(String type, String subtype){
        switch(type.toLowerCase()){
            case "crisis":
                return createCrisisEvent(subtype);
            case "medication":
                return  createMedicationEvent(subtype);
            default:
                throw new IllegalArgumentException("Invalid event type");
        }
    }

    public static Event createCrisisEvent(String subtype){
        switch (subtype.toLowerCase()){
            case "timeExceeded":
                return new CrisisEvent.TimeExceededEvent();
            case "registered":
                return new CrisisEvent.RegistredCrisisEvent();
            case "started":
                return new CrisisEvent.StartedCrisis();
            default:
                throw new IllegalArgumentException("Invalid event subtype");
        }
    }

    public static Event createMedicationEvent(String subtype){
        switch (subtype.toLowerCase()){
            case "hasToBeTaken":
                return new MedicationEvent.MedicationHasToBeTakenEvent();
            case "taken":
                return new MedicationEvent.MedicationTakenEvent();
            default:
                throw new IllegalArgumentException("Invalid event subtype");
        }
    }
}
