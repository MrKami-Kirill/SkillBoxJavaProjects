import Accounts.CardAccount;
import Accounts.DepositAccount;
import Accounts.PaymentAccount;

public class Main {
    public static void main(String[] args) {
        PaymentAccount myAccount = new PaymentAccount(1000000);
        DepositAccount myDepositAccount = new DepositAccount(10000000, 90); //Установить кол-во дней с последнего снятия.
        CardAccount myCardAccount = new CardAccount(1000000);
        System.out.println();
        System.out.println("Текущий счет");
        System.out.println("Остаток на счете: " + myAccount.getBalance() + " рублей.");
        myAccount.addBalance(1000);
        System.out.println("Остаток на счете: " + myAccount.getBalance() + " рублей.");
        myAccount.addBalance(-1000);
        System.out.println("Остаток на счете: " + myAccount.getBalance() + " рублей.");
        System.out.println();
        System.out.println("Депозитный счет");
        System.out.println("Остаток на депозитном счете: " + myDepositAccount.getBalance() + " рублей.");
        myDepositAccount.addBalance(-1000);
        System.out.println("Остаток на депозитном счете: " + myDepositAccount.getBalance() + " рублей.");
        System.out.println();
        System.out.println("Карточный счет");
        System.out.println("Остаток на карточном счете: " + myCardAccount.getBalance() + " рублей.");
        myCardAccount.addBalance(-1000); //Комиссия 10 рублей (1%)
        System.out.println("Остаток на карточном счете: " + myCardAccount.getBalance() + " рублей.");
    }
}
