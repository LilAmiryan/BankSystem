package com.example.banksystem.model.enums;

public enum CardType {
    MASTER_CARD(1, "MASTER_CARD"),
    VISA(2, "VISA"),
    ARCA(3, "ARCA"),
    AMERICAN_EXPRESS(4, "AMERICAN_EXPRESS");

    private final int id;
    private final String type;

    private CardType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public static CardType findById(int id) {
        for (CardType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
