package com.example.episafezone.DTO.SharedDTO;

import java.io.Serializable;

public class SharePatientDTO implements Serializable {
    private Integer tutorSharingId;
    private String tutorReceivingEmail;
    private Integer patientId;
    private Boolean registerCrisisPermission;
    private Boolean profilePermission;
    private Boolean medicinePermission;
    private Boolean tutorPermission;

    public SharePatientDTO(Integer tutorSharingId, String tutorReceivingEmail, Integer patientId,
                           Boolean registerCrisisPermission, Boolean profilePermission, Boolean medicinePermission,
                           Boolean tutorPermission) {
        this.tutorSharingId = tutorSharingId;
        this.tutorReceivingEmail = tutorReceivingEmail;
        this.patientId = patientId;
        this.registerCrisisPermission = registerCrisisPermission;
        this.profilePermission = profilePermission;
        this.medicinePermission = medicinePermission;
        this.tutorPermission = tutorPermission;
    }

    public SharePatientDTO(){}

    public Integer getTutorSharingId() {
        return tutorSharingId;
    }

    public void setTutorSharingId(Integer tutorSharingId) {
        this.tutorSharingId = tutorSharingId;
    }

    public String getTutorReceivingEmail() {
        return tutorReceivingEmail;
    }

    public void setTutorReceivingEmail(String tutorReceivingEmail) {
        this.tutorReceivingEmail = tutorReceivingEmail;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Boolean getRegisterCrisisPermission() {
        return registerCrisisPermission;
    }

    public void setRegisterCrisisPermission(Boolean registerCrisisPermission) {
        this.registerCrisisPermission = registerCrisisPermission;
    }

    public Boolean getProfilePermission() {
        return profilePermission;
    }

    public void setProfilePermission(Boolean profilePermission) {
        this.profilePermission = profilePermission;
    }

    public Boolean getMedicinePermission() {
        return medicinePermission;
    }

    public void setMedicinePermission(Boolean medicinePermission) {
        this.medicinePermission = medicinePermission;
    }

    public Boolean getTutorPermission() {
        return tutorPermission;
    }

    public void setTutorPermission(Boolean tutorPermission) {
        this.tutorPermission = tutorPermission;
    }
}
