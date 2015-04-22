package com.github.katalisthenics.operation;

import com.github.katalisthenics.Amount;

public abstract class AddOperation extends Operation {

    public Amount compute(Amount totalAmount) {
        return totalAmount.add(amount);
    }
}
