package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {

    public static Bank bank;
    public static Double bigTransactionProbability = 0.15; //вероятность выпадения транзакции, которая отправится на проверку СБ
    public static Integer numbersOfAccounts = 10; //Кол-во счетов
    public static Integer numbersOfTransactions = 1000; //Кол-во транзакций
    public static Integer threads = Runtime.getRuntime().availableProcessors(); //Кол-во потоков (= CPU cores)
    static AtomicInteger transferCounter = new AtomicInteger();

    public static void main(String[] args) {
        System.out.println("Создаем новый Банк!");
        bank = new Bank();
        for (int i = 0; i < numbersOfAccounts; i++) {
            String clientNumber = String.valueOf(i);
            Long balance = (long) (Math.pow(Math.random(), Math.random() * 10) * 10000000); //случайный баланс на счете
            Account account = new Account(balance, generationAccountNumber("40817", "810", "044525225"), AccStatus.OPEN);
            bank.addAccount(clientNumber, account);
        }
        System.out.println("Всего счетов в Банке: " + bank.getAccounts().size());

        for (int i = 1; i <= threads; i++) {
            int finalI = i;
            new Thread(() -> {
                while (transferCounter.get() < numbersOfTransactions) {
                    transferCounter.incrementAndGet();
                    Account randomAccountFrom = getRandomAccount();
                    Account randomAccountTo = getRandomAccount();
                    while (randomAccountFrom.equals(randomAccountTo)) {
                        randomAccountTo = getRandomAccount();
                    }
                    double random = Math.random(); //Случайная вероятность
                    long transferAmount;
                    if (random < bigTransactionProbability) {
                        transferAmount = 50000 + (long) (Math.random() * Math.random() * 1000000);
                    } else {
                        transferAmount = (long) (Math.random() * 50000);
                    }
                    bank.transfer(randomAccountFrom.getAccNumber(), randomAccountTo.getAccNumber(), transferAmount);
                    System.out.println("Поток №" + finalI + ": " +
                            "Транзакция №" + transferCounter.get() +
                            " со счета " + randomAccountFrom.getAccNumber() + " на счет " + randomAccountTo.getAccNumber() +
                            " на сумму " + transferAmount);
                }
        }).start();
    }
        //Выводим список заблокированных счетов после нажатия Enter
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("Список заблокированных счетов:");
        bank.getAccounts().values().forEach(j -> {
            if (j.isBlocked()) {
                System.out.print(j.getAccNumber() + "\n");
            }
        });
}

    private static Account getRandomAccount() {
        Account account = null;
        int keyCount = (int) (Math.random() * numbersOfAccounts);
        int count = 0;
        for (HashMap.Entry<String, Account> entry : bank.getAccounts().entrySet()) {
            if (count == keyCount) {
                account = entry.getValue();
                break;
            } else {
                count++;
            }
        }
        return account;
    }

    public static String generationAccountNumber(String secondOrderAccount, String currency, String bankIdentificationCode) { //Генерация контрольного ключа и номера счета согласно (утв. Банком России 08.09.1997 N 515).
        // Link: http://www.consultant.ru/document/cons_doc_LAW_16053/

        List<Integer> lastCharList = new ArrayList<>();
        List<Integer> randomAccNumberList = new ArrayList<>();
        // Генерация 11-20 символов номера счета

        int a = 0; // Начальное значение диапазона - "от"
        int b = 10; // Конечное значение диапазона - "до"
        for (int i = 0; i < 11; i++) { //количество рандомных символов в аккаунте после контрольного ключа (кол-во = 11)
            randomAccNumberList.add(a + (int) (Math.random() * b));
        }

        for (int i = 0; i <= 2; i++) {
            lastCharList.add(getLastChar(bankIdentificationCode, (i + 6), i));
        }
        for (int i = 0; i <= 4; i++) {
            lastCharList.add(getLastChar(secondOrderAccount, i, (i + 3)));
        }
        for (int i = 0; i <= 2; i++) {
            lastCharList.add(getLastChar(currency, i, (i + 8)));
        }
        for (int i = 0; i < randomAccNumberList.size(); i++) {
            lastCharList.add(randomAccNumberList.get(i) * getLastChar(null, null, (i + 12)));
        }
        int sumLastChar = 0;
        for (int i = 0; i < lastCharList.size(); i++) {
            sumLastChar += lastCharList.get(i);
        }
        int controlKeyValue = ((sumLastChar%10) * 3)%10;
        String controlKey = Integer.toString(controlKeyValue);
        String accountNumber = secondOrderAccount + currency + controlKey + toString(randomAccNumberList);
        return accountNumber;
    }

    public static Integer getLastChar (String i, Integer x, Integer y) {
        String weightingFactors = "71371371371371371371371"; //весовые коэффициенты
        Integer lastchar = 0;
        if (i != null && x != null) {
            lastchar = (Character.digit(i.charAt(x), 10) * Character.digit(weightingFactors.charAt(y), 10)) % 10; }
        else {
            lastchar = (Character.digit(weightingFactors.charAt(y), 10))%10; }
        return lastchar; }


    public static String toString(List<Integer> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i);
        }
        return str;
    }
}
