package com.aphart.myguardian.enums;

/**
 * Created by aphart on 4/24/2017.
 */

public enum SignInDataEnum {
    GENDER_ID("GENDER_ID");

    private final String name;

    private SignInDataEnum(String s) {
        name = s;
    }


    public boolean equalsName(String otherName) {

        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
