package com.github.katalisthenics.operation;

import com.github.katalisthenics.Amount;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Operation implements Comparable<Operation> {

    Date date;
    Amount amount;

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = formatter.format(this.date);
        return getType() + " - " + date + " - " + amount.toString();
    }

    public abstract Amount compute(Amount totalAmount);

    public abstract TypeOperation getType();

    @Override
    public int compareTo(Operation o) {
        return amount.compareTo(o.amount);
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
