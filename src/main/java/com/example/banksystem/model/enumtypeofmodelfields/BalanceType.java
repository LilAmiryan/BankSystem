package com.example.banksystem.model.enumtypeofmodelfields;

public enum BalanceType {
    DEBIT(1, "DEBIT"),
    CREDIT(2, "CREDIT");

    private final int id;
    private final String type;

    BalanceType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public BalanceType findById(int id) {
        for (BalanceType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

}
