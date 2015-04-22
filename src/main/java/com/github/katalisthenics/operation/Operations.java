package com.github.katalisthenics.operation;

import com.github.katalisthenics.Amount;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Operations {

    List<Operation> operations = new ArrayList<>();

    public Operations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public void transfertInOperation(Date date, Amount amount) {
        operations.add(new TransfertInOperation(date, amount));
    }

    public void transfertOutOperation(Date date, Amount amount) {
        operations.add(new TransfertOutOperation(date, amount));
    }

    public void withdrawOperation(Date date, Amount amount) {
        operations.add(new WithdrawOperation(date, amount));
    }

    public void depositOperation(Date date, Amount amount) {
        operations.add(new DepositOperation(date, amount));
    }

    public Operations filter(Predicate<Operation> predicate) {
        operations = FluentIterable.from(operations).filter(predicate).toList();
        return this;
    }

    public Operations clone() {
        ArrayList<Operation> operationsDest = new ArrayList<>();
        operationsDest.addAll(this.operations);
        return new Operations(operationsDest);
    }

    public Amount sold() {
        Amount amount = new Amount(0);
        for (Operation operation : operations) {
            amount = operation.compute(amount) ;
        }
        return amount;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
