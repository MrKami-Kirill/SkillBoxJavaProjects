package Accounts;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DepositAccount extends PaymentAccount {
    private int days;
    LocalDate now = LocalDate.now();
    LocalDate daysBehind = now.minusDays(days); //За сколько дней был совершен платеж
    Period period = Period.between(now, daysBehind);
    public DepositAccount(int balance, int days) {
        super(balance);
        this.days = days;
    }
    public int getBalance() {
        return balance;
    }

     private void setBalance(int balance) {
        this.balance += balance;
    }

    public void addBalance(int balance) {
        if (balance > 0) {
            System.out.println("Положить на счет " + balance + " рублей." );
            this.balance += balance;
        }
        if ((balance < 0) && (period.getDays() >= 30)) {
            System.out.println("Снять со счета " + ((-1) * balance) + " рублей.");
            this.balance += balance;
        }
        if ((balance < 0) && (period.getDays() < 30)) {
            System.out.println("Невозможно снять деньги, т.к. с последнего пополнения прошло меньше 30 дней");
        }
    }
}
