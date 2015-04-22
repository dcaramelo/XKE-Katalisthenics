package com.github.katalisthenics;

import com.github.katalisthenics.operation.Operation;
import com.github.katalisthenics.operation.Operations;
import com.google.common.base.Predicate;

import java.text.SimpleDateFormat;

public class FilterReport {

    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Client client;
    Operations operations;

    public FilterReport(Client client, Operations filter) {
        this.client = client;
        this.operations = filter.clone();
    }

    public FilterReport filter(Predicate<Operation> predicate) {
        operations = operations.filter(predicate);
        return this;
    }

    public String print() {
        StringBuilder sb = new StringBuilder(30);
        sb.append(client.toString());
        sb.append("\n----------------------");
        for (Operation operation : operations.getOperations()) {
            sb.append("\n").append(formatLine(operation));
        }
        sb.append("\n----------------------");

        return sb.toString();
    }

    private String formatLine(Operation operation) {
        return String.format("%-13s - Amount : %4d - Date : %-13s",
                operation.getType().getLabel(),
                operation.getAmount().getValue(),
                formatter.format(operation.getDate()));
    }
}
