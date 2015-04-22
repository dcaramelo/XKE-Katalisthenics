package com.github.katalisthenics.predicate;

import com.github.katalisthenics.operation.Operation;
import com.google.common.base.Predicate;

import static com.github.katalisthenics.operation.TypeOperation.DEPOSIT;
import static com.github.katalisthenics.operation.TypeOperation.TRANSFERT_OUT;
import static com.github.katalisthenics.operation.TypeOperation.TRANSFERT_IN;
import static com.github.katalisthenics.operation.TypeOperation.WITHDRAW;

public class Predicates {

    public static Predicate<Operation> depositFilter() {
        return operation -> operation.getType() == DEPOSIT;
    }

    public static Predicate<Operation> withdrawFilter() {
        return operation -> operation.getType() == WITHDRAW;
    }

    public static Predicate<Operation> transfertOutFilter() {
        return operation -> operation.getType() == TRANSFERT_OUT;
    }

    public static Predicate<Operation> transfertInFilter() {
        return operation -> operation.getType() == TRANSFERT_IN;
    }
}
