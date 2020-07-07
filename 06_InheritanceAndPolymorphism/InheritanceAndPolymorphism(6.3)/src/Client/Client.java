package Client;

/**
 * Абстрактный  класс с методами по изменению и получению баланса Клиента
 * @author Kirill Galeev
 * @version 1.0
 */
public abstract class Client {
    /**
     * Защищенная переменная для пакета CLient, отвечающая за баланс Клиента
     * @author Kirill Galeev
     * @version 1.0
     */
    protected double balance;

    public Client(double balance) {
        this.balance = balance;
    }


    public double getBalance() {
        System.out.println("Остаток на счете: " + balance + " рублей.");
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Защищенный метод, отвечающий за изменение баланса Клиента
     * @param balance
     * @author Kirill Galeev
     * @version 1.0
     */
    protected abstract void addBalance (double balance);


}
