package com.aphart.myguardian.enums;

/**
 * Created by aphart on 4/20/2017.
 */

public enum OperationEnum {
    CREATE("CREATE"), UPDATE("UPDATE"), READ("READ"), DELETE("DELETE");

    private final String name;

    private OperationEnum(String s) {
        name = s;
    }


    public boolean equalsName(String otherName) {

        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
