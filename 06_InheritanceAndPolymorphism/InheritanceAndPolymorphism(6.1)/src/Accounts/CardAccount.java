package Accounts;

public class CardAccount extends PaymentAccount {

    public CardAccount(int balance) {
        super(balance);
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance += balance;
    }

    public void addBalance(int balance) {
        int commission = (int) (balance * 0.01);
        if (balance > 0) {
            System.out.println("Положить на счет " + balance + " рублей." );
            this.balance += balance;
        }
        if (balance < 0) {
            System.out.println("Снять со счета " + ((-1) * balance) + " рублей.");
            this.balance += (balance + commission);
            System.out.println("Комиссия составила: " + ((-1) * commission) + " рублей");
        }
    }
}
