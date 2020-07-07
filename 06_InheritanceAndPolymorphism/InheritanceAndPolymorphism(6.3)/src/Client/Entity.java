package Client;

public class Entity extends Client {

    /**
     * Класс Клиента - "Юридическое лицо"
     * @param balance
     * @author Kirill Galeev
     * @version 1.0
     */
    public Entity(double balance) {
        super(balance);

    }
    /**
     * Метод, отвечающий за изменение баланса Клиента (уменьшение баланса с комиссией 1%)
     * @param balance
     * @author Kirill Galeev
     * @version 1.0
     */
    @Override
    public void addBalance(double balance) {
        int commision = (int) (balance * 0.01);
        if (balance < 0) {
            System.out.println("Снять со счета " + ((-1) * balance) + " рублей.");
            this.balance += balance + commision;
            System.out.println("Комиссия составила: " + commision + " рублей.");
        }
        if (balance > 0) {
            System.out.println("Положить на счет " + balance + " рублей.");
            this.balance += balance;
        }

    }
}
