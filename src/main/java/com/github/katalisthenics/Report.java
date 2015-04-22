package com.github.katalisthenics;

import com.github.katalisthenics.operation.Operation;
import com.github.katalisthenics.operation.Operations;
import com.google.common.base.Predicate;

import java.text.SimpleDateFormat;

public class Report {

    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Client client;
    Operations operations;

    public Report(Client client, Operations operations) {
        this.client = client;
        this.operations = operations.clone();
    }

    public FilterReport filter(Predicate<Operation> predicate) {
        return new FilterReport(client, operations.filter(predicate));
    }

    public String print() {
        StringBuilder sb = new StringBuilder(30);
        Amount balance = new Amount(0);
        sb.append(client.toString());
        sb.append("\n----------------------");
        for (Operation operation : operations.getOperations()) {
            balance = operation.compute(balance);
            sb.append("\n").append(formatLine(balance, operation));
        }
        sb.append("\n----------------------");
        sb.append("\nSolde : " + balance.getValue());
        return sb.toString();
    }

    private String formatLine(Amount balance, Operation operation) {
        return String.format("%-13s - Amount : %4d - Date : %-13s - Balance : %4d",
                operation.getType().getLabel(),
                operation.getAmount().getValue(),
                formatter.format(operation.getDate()),
                balance.getValue());
    }
}
