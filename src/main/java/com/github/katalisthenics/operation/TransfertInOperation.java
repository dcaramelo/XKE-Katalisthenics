package com.github.katalisthenics.operation;

import com.github.katalisthenics.Amount;

import java.util.Date;

public class TransfertInOperation extends AddOperation {

    public TransfertInOperation(Date date, Amount amount) {
        this.amount = amount;
        this.date = date;
    }

    public TypeOperation getType() {
        return TypeOperation.TRANSFERT_IN;
    }
}
