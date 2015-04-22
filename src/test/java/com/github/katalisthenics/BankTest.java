package com.github.katalisthenics;

import org.junit.Test;

import java.util.Date;

import static com.github.katalisthenics.predicate.Predicates.depositFilter;
import static com.github.katalisthenics.predicate.Predicates.transfertInFilter;
import static com.google.common.base.Predicates.not;
import static org.assertj.core.api.Assertions.assertThat;


public class BankTest {

    @Test
    public void client_deposit_500() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);

        // when
        account.operations().depositOperation(date, new Amount(500));

        // then
        assertThat(account.solde()).isEqualTo(new Amount(500));
    }

    @Test
    public void client_withdraw_100() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));

        // when
        account.operations().withdrawOperation(date, new Amount(100));

        // then
        assertThat(account.solde()).isEqualTo(new Amount(400));
    }

    @Test
    public void account_operation_storage() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(100));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(400));
        account.operations().depositOperation(date, new Amount(1000));

        // when
        Amount accountAmount = account.solde();

        // then
        assertThat(accountAmount).isEqualTo(new Amount(2000));
    }

    @Test
    public void account_operation_with_transfert_in() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(100));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(400));
        account.operations().transfertInOperation(date, new Amount(1000));

        // when
        Amount accountAmount = account.solde();

        // then
        assertThat(accountAmount).isEqualTo(new Amount(2000));
    }

    @Test
    public void account_operation_with_transfert_out() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(100));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(400));
        account.operations().transfertOutOperation(date, new Amount(1000));

        // when
        Amount accountAmount = account.solde();

        // then
        assertThat(accountAmount).isEqualTo(new Amount(0));
    }

    @Test
    public void transfert_between_two_account() {
        // given
        Date date = new Date(1429702767765L);
        Client clientSource = new Client(new Name("Mick", "Caramelo"));
        Account accountSource = new Account(clientSource);
        accountSource.operations().depositOperation(date, new Amount(500));

        Client clientDest= new Client(new Name("Mick", "Caramelo"));
        Account accountDest = new Account(clientDest);

        // when
        accountSource.transfert(date, accountDest, new Amount(100));

        // then
        assertThat(accountDest.solde()).isEqualTo(new Amount(100));
        assertThat(accountSource.solde()).isEqualTo(new Amount(400));
    }

    @Test
    public void report() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(100));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(400));
        account.operations().transfertInOperation(date, new Amount(1000));

        // when
        String report = account.report().print();

        // thenQ
        assertThat(report).isEqualTo(
                "client : Lastname : David - Firstname : Caramelo\n" +
                "----------------------\n" +
                "Deposit       - Amount :  500 - Date : 22/04/2015 13:39:27 - Balance :  500\n" +
                "Withdraw      - Amount :  100 - Date : 22/04/2015 13:39:27 - Balance :  400\n" +
                "Deposit       - Amount :  500 - Date : 22/04/2015 13:39:27 - Balance :  900\n" +
                "Deposit       - Amount :  500 - Date : 22/04/2015 13:39:27 - Balance : 1400\n" +
                "Withdraw      - Amount :  400 - Date : 22/04/2015 13:39:27 - Balance : 1000\n" +
                "Transfert in  - Amount : 1000 - Date : 22/04/2015 13:39:27 - Balance : 2000\n" +
                "----------------------\n" +
                "Solde : 2000");
    }

    @Test
    public void report_filter_deposit() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(100));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().depositOperation(date, new Amount(400));
        account.operations().withdrawOperation(date, new Amount(400));
        account.operations().transfertInOperation(date, new Amount(1000));

        // when
        String report = account.report().filter(depositFilter()).print();

        // then
        assertThat(report).isEqualTo(
                "client : Lastname : David - Firstname : Caramelo\n" +
                "----------------------\n" +
                "Deposit       - Amount :  500 - Date : 22/04/2015 13:39:27\n" +
                "Deposit       - Amount :  500 - Date : 22/04/2015 13:39:27\n" +
                "Deposit       - Amount :  400 - Date : 22/04/2015 13:39:27\n" +
                "----------------------");
    }

    @Test
    public void report_filter_without_deposit() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(100));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().depositOperation(date, new Amount(400));
        account.operations().withdrawOperation(date, new Amount(400));
        account.operations().transfertInOperation(date, new Amount(1000));

        // when
        String report = account.report().filter(not(depositFilter())).print();

        // then
        assertThat(report).isEqualTo(
                "client : Lastname : David - Firstname : Caramelo\n" +
                "----------------------\n" +
                "Withdraw      - Amount :  100 - Date : 22/04/2015 13:39:27\n" +
                "Withdraw      - Amount :  400 - Date : 22/04/2015 13:39:27\n"+
                "Transfert in  - Amount : 1000 - Date : 22/04/2015 13:39:27\n"+
                "----------------------");
    }

    @Test
    public void report_filter_without_deposit_and_transfert_in() {
        // given
        Date date = new Date(1429702767765L);
        Client client = new Client(new Name("David", "Caramelo"));
        Account account = new Account(client);
        account.operations().depositOperation(date, new Amount(500));
        account.operations().withdrawOperation(date, new Amount(100));
        account.operations().depositOperation(date, new Amount(500));
        account.operations().depositOperation(date, new Amount(400));
        account.operations().withdrawOperation(date, new Amount(400));
        account.operations().transfertInOperation(date, new Amount(1000));

        // when
        String report = account.report()
                .filter(not(depositFilter()))
                .filter(not(transfertInFilter()))
                .print();

        // then
        assertThat(report).isEqualTo(
                "client : Lastname : David - Firstname : Caramelo\n" +
                        "----------------------\n" +
                        "Withdraw      - Amount :  100 - Date : 22/04/2015 13:39:27\n" +
                        "Withdraw      - Amount :  400 - Date : 22/04/2015 13:39:27\n"+
                        "----------------------");
    }
}
