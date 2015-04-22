package com.github.katalisthenics.operation;

public enum TypeOperation {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw"),
    TRANSFERT_OUT("Transfert out"),
    TRANSFERT_IN("Transfert in");

    private String label;

    TypeOperation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
