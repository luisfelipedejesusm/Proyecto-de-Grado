package com.luisfelipedejesusm.final_project.Enums;

public enum EBloodType {
    A_NEGATIVE("A-"),
    A_POSITIVE("A+"),
    B_NEGATIVE("B-"),
    B_POSITIVE("B+"),
    O_NEGATIVE("O-"),
    O_POSITIVE("O+"),
    AB_NEGATIVE("AB-"),
    AB_POSITIVE("AB+");

    private String name;

    EBloodType(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
