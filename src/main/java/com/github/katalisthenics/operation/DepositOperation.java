package com.github.katalisthenics.operation;

import com.github.katalisthenics.Amount;

import java.util.Date;

public class DepositOperation extends AddOperation {

    public DepositOperation(Date date, Amount amount) {
        this.amount = amount;
        this.date = date;
    }

    public TypeOperation getType() {
        return TypeOperation.DEPOSIT;
    }
}
