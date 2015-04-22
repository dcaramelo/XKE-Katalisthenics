package com.github.katalisthenics.operation;

import com.github.katalisthenics.Amount;

import java.util.Date;

import static com.github.katalisthenics.operation.TypeOperation.WITHDRAW;

public class WithdrawOperation extends SubOperation {

    public WithdrawOperation(Date date, Amount amount) {
        this.amount = amount;
        this.date = date;
    }

    public TypeOperation getType() {
        return WITHDRAW;
    }
}
