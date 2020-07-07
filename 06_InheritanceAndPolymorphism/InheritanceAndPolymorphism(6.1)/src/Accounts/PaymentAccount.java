package Accounts;

public class PaymentAccount {
    protected int balance;

    public PaymentAccount(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    public void addBalance(int balance) {
        if (balance > 0) {
            System.out.println("Положить на счет " + balance + " рублей.");
            this.balance += balance;
        }
        if (balance < 0) {
            System.out.println("Снять со счета " + ((-1) * balance) + " рублей.");
            this.balance += balance;
        }
    }
}