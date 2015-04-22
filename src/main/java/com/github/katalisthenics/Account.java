package com.github.katalisthenics;

import com.github.katalisthenics.operation.Operations;

import java.util.ArrayList;
import java.util.Date;

public class Account {

    Client client;
    Operations operations;


    public Account(Client client) {
        this.client = client;
        this.operations = new Operations(new ArrayList<>());
    }

    public Operations operations() {
        return operations;
    }

    public Amount solde() {
        return operations.sold();
    }

    public void transfert(Date date, Account accountDest, Amount amount) {
        operations.transfertOutOperation(date, amount);
        accountDest.operations().transfertInOperation(date, amount);
    }

    public Report report() {
        return new Report(this.client, this.operations);
    }
}
