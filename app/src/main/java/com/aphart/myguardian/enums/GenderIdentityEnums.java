package com.aphart.myguardian.enums;

/**
 * Created by aphart on 4/23/2017.
 */

public enum GenderIdentityEnums {
    MALE("MALE"), FEMALE("FEMALE"), NONBINARY("NONBINARY");

    private final String name;

    private GenderIdentityEnums(String s) {
        name = s;
    }


    public boolean equalsName(String otherName) {

        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
