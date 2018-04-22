package com.hashpet.pojo;

public class PetInfo {
    private Integer petId;

    private String petName;

    private String petGender;

    private String petColor;

    private Integer petProId;

    private String petClass;

    private Integer petLength;

    private Integer petAge;

    private Integer petHotLevel;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName == null ? null : petName.trim();
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender == null ? null : petGender.trim();
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor == null ? null : petColor.trim();
    }

    public Integer getPetProId() {
        return petProId;
    }

    public void setPetProId(Integer petProId) {
        this.petProId = petProId;
    }

    public String getPetClass() {
        return petClass;
    }

    public void setPetClass(String petClass) {
        this.petClass = petClass == null ? null : petClass.trim();
    }

    public Integer getPetLength() {
        return petLength;
    }

    public void setPetLength(Integer petLength) {
        this.petLength = petLength;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }

    public Integer getPetHotLevel() {
        return petHotLevel;
    }

    public void setPetHotLevel(Integer petHotLevel) {
        this.petHotLevel = petHotLevel;
    }
}