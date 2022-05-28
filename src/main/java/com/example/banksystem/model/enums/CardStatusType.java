package com.example.banksystem.model.enums;

public enum CardStatusType {
    ACTIVE(1, "BLOCKED"),
    BLOCKED(2, "EXPIRED");

    private final int id;
    private final String type;

    CardStatusType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public CardStatusType findById(int id) {
        for (CardStatusType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

}
