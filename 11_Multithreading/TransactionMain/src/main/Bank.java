package main;

import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank
{
    private ConcurrentHashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        accounts = new ConcurrentHashMap<>();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account fromAcc = getAccount(fromAccountNum);
        Account toAcc = getAccount(toAccountNum);
        try {

            if (toAcc.isBlocked()) {
                System.out.println("Транзакция невозможна, т.к. счет " + fromAcc.getAccNumber() + " заблокирован!");
            } else if (fromAcc.isBlocked()) {
                System.out.println("Транзакция невозможна, т.к. счет " + toAcc.getAccNumber() + " заблокирован!");
            } else if (toAcc.isBlocked() && fromAcc.isBlocked()) {
                System.out.println("Транзакция невозможна, т.к. следующие счета заблокированы:\n" + toAcc.getAccNumber() + "\n" + fromAcc.getAccNumber());
            } else if (!toAcc.isBlocked()) {
                if (fromAcc.getMoney() >= amount) {
                    long balance = fromAcc.getMoney();
                    balance -= amount;
                    fromAcc.setMoney(balance);
                    balance = toAcc.getMoney() + balance;
                    toAcc.setMoney(balance);
                    if (amount >= 50000) {
                        isFraud(fromAccountNum, toAccountNum, amount);
                        blockedAccounts(fromAcc, toAcc);
                        System.out.println("Транзакция направлена на проверкку Службой безопасности!");
                    } else {
                        System.out.println("Транзакция проведена! " + fromAcc.getAccNumber() + " -> " + toAcc.getAccNumber() + ": " + amount + " RUR");
                    }
                } else {
                    System.out.println("Недостаточно средств на счете для совершения транзакции!");
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public Long getBalance(String accountNum) {
        if (getAccount(accountNum) != null) {
        return getAccount(accountNum).getMoney();
        } else {
            System.out.println("Проверить остаток на счете невозможно, т.к. запрашиваемо счета не существует!");
            return null;
        }
    }

    public Account getAccount(String accNumber) {
        for (Account account : accounts.values()) {
            if (account.getAccNumber().equals(accNumber)) {
                return account;
            }
        }
        System.out.println("Счет в базе не найден");
        return null;
    }

    public void blockedAccounts(Account fromAcc, Account toAcc) {
        fromAcc.setAccStatus(AccStatus.BLOCKED);
        toAcc.setAccStatus(AccStatus.BLOCKED);
        System.out.println("Следующие счета заблокированы: \n" + fromAcc.getAccNumber() + "\n" + toAcc.getAccNumber());
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ConcurrentHashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(String name, Account account) {
        accounts.put(name, account);
    }
}

