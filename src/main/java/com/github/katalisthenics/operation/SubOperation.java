package com.github.katalisthenics.operation;

import com.github.katalisthenics.Amount;

public abstract class SubOperation extends Operation {

    public Amount compute(Amount totalAmount) {
        return totalAmount.sub(amount);
    }
}
