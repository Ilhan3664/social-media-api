package org.example.socialmediaapi.constants;

public enum Status {

    ACTIVE(1),
    INACTIVE(0);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
