package com.github.katalisthenics;

public class Amount implements Comparable<Amount> {
    private int value;

    public Amount(int amountValue) {
        value = amountValue;
    }

    public Amount add(Amount addAmount) {
        return new Amount(value + addAmount.value);
    }

    public Amount sub(Amount subAmount) {
        return new Amount(value - subAmount.value);
    }

    public int getValue() {
        return value;
    }

    public boolean equals(Object o) {
        Amount amount1 = (Amount) o;
        return value == amount1.value;
    }

    public int hashCode() {
        return value;
    }

    @Override
    public int compareTo(Amount o) {
        return Integer.compare(value, o.value);
    }
}
