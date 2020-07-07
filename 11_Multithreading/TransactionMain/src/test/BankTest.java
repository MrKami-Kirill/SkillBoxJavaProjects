package test;

import junit.framework.TestCase;
import main.AccStatus;
import main.Account;
import main.Bank;

public class BankTest extends TestCase {

    Bank bank;
    private String firstClientName = "1st Client";
    private String secondClientName = "2nd Client";
    private String accNumberFirstClient = "40817810410366988186";
    private String accNumberSecondClient = "40817810504031943278";
    private long firstBalance = 100000;
    private long secondBalance = 300000;

    @Override
    protected void setUp() throws Exception {
        bank = new Bank();
        bank.addAccount(firstClientName, new Account(firstBalance, accNumberFirstClient, AccStatus.OPEN));
        bank.addAccount(secondClientName, new Account(secondBalance, accNumberSecondClient, AccStatus.OPEN));
    }

    public void testgetBalance() {
        long actual = bank.getBalance(accNumberFirstClient);
        long expected = 100000;
        assertEquals(expected, actual);
    }

    public void testTransfer() {
        bank.transfer(accNumberFirstClient, accNumberSecondClient, 40000);
        long actual = bank.getBalance(accNumberFirstClient);
        long expected = 60000;
        assertEquals(expected, actual);
    }

    public void testTransferBlocked() {
        bank.transfer(accNumberFirstClient, accNumberSecondClient, 80000);
        Account firstAccount = bank.getAccount(accNumberFirstClient);
        Account secondAccount = bank.getAccount(accNumberSecondClient);
        if (!bank.getAccount(accNumberSecondClient).isBlocked()) {
            bank.blockedAccounts(firstAccount, secondAccount);
        }
        Boolean expected = true;
        Boolean actual = bank.getAccount(accNumberFirstClient).isBlocked();
        assertEquals(expected, actual);
    }

    public  void testTransferBlockedOneAccount() {
        bank.getAccount(accNumberFirstClient).setAccStatus(AccStatus.BLOCKED);
        bank.transfer(accNumberFirstClient, accNumberSecondClient, 20000);
        Boolean expected = true;
        Boolean actual = bank.getAccount(accNumberFirstClient).isBlocked();
        assertEquals(expected, actual);
    }

    public void testTransferBlockedTwoAccount() {
        bank.getAccount(accNumberFirstClient).setAccStatus(AccStatus.BLOCKED);
        bank.getAccount(accNumberSecondClient).setAccStatus(AccStatus.BLOCKED);
        bank.transfer(accNumberFirstClient, accNumberSecondClient, 20000);
        Boolean expected = true;
        Boolean actual = bank.getAccount(accNumberFirstClient).isBlocked() && bank.getAccount(accNumberSecondClient).isBlocked();
        assertEquals(expected, actual);
    }

    public void testTransferAccountLowBalance() {
        bank.getAccount(accNumberFirstClient).setMoney(20000);
        bank.transfer(accNumberFirstClient, accNumberSecondClient, 100000);
    }

    public void testBlockAccounts() {
        Account firstAccount = bank.getAccount(accNumberFirstClient);
        Account secondAccount = bank.getAccount(accNumberSecondClient);
        bank.blockedAccounts(firstAccount, secondAccount);
        Boolean expected = true;
        Boolean actual = bank.getAccount(accNumberFirstClient).isBlocked() && bank.getAccount(accNumberSecondClient).isBlocked();
        assertEquals(expected, actual);
    }
}
