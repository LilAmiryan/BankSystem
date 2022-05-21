package com.example.banksystem.model.enumtypeofmodelfields;

import com.example.banksystem.model.Bank;

public enum BankType {
    CENTRAL_BANK(1, "CENTRAL_BANK"),
    COMMERCIAL_BANK(2, "COMMERCIAL_BANK"),
    SPECIALISED_BANK(3, "SPECIALISED_BANK"),
    COOPERATIVE_BANK(4, "COOPERATIVE_BANK");

    private final int id;
    private final String type;

    BankType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public BankType findById(int id) {
        for (BankType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

}
