package com.example.banksystem.model.enumtypeofmodelfields;

public enum CardStatusType {
    CREATED(1, "CREATED"),
    ACTIVE(2, "ACTIVE"),
    BLOCKED(3, "BLOCKED");

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
