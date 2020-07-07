package Client;

public class IndividualEntrepreneur extends Client {

    /**
     * Класс Клиента - "Индивидуальный предприниматель"
     * @param balance
     * @author Kirill Galeev
     * @version 1.0
     */
    public IndividualEntrepreneur(double balance) {
        super(balance);
    }

    /**
     * @param balance
     */
    @Override
    /**
     * Метод, отвечающий за изменение баланса Клиента (увеличение баланса меньше 1000 с комиссией 1%, больше 1000 - 0.5%)
     * @param balance
     * @author Kirill Galeev
     * @version 1.0
     */
    public void addBalance(double balance) {
        int commision1 = (int) (balance * 0.01);
        int commision2 = (int) (balance * 0.005);
        if (balance < 0) {
            System.out.println("Снять со счета " + ((-1) * balance) + " рублей.");
            this.balance += balance;

        }
        if (balance > 0 && balance < 1000) {
            System.out.println("Положить на счет " + balance + " рублей.");
            this.balance += (balance - commision1);
            System.out.println("Комиссия составила: " + commision1 + " рублей.");
        }
        if (balance >= 1000) {
            System.out.println("Положить на счет " + balance + " рублей.");
            this.balance += (balance - commision2);
            System.out.println("Комиссия составила: " + commision1 + " рублей.");
        }
    }
}
