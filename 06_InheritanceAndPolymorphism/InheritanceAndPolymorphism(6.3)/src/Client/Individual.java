package Client;

public class Individual extends Client {
    /**
     * Класс Клиента - "Физическое лицо"
     * @param balance
     * @author Kirill Galeev
     * @version 1.0
     */
    public Individual(double balance) {
        super(balance);
    }

    @Override
    /**
     * Метод, отвечающий за изменение баланса Клиента
     * @param balance
     * @author Kirill Galeev
     * @version 1.0
     */
    public void addBalance(double balance) {
        if (balance < 0) {
            System.out.println("Снять со счета " + ((-1) * balance) + " рублей.");
        }
        if (balance > 0) {
            System.out.println("Положить на счет " + balance + " рублей.");
        }
        this.balance += balance;
    }
}

