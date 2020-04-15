package com.habbib.enumerator;

public enum InstitutionType {
    PrivateHospital(0),
    PublicHospital(1);

    private int value;

    InstitutionType(int value) {
        this.value = value;
    }
}
