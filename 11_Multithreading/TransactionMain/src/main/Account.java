package main;

public class Account
{
    private long money;
    private String accNumber;
    private AccStatus accStatus;

    public Account (long money, String accNumber, AccStatus accStatus) {
        this.accNumber = accNumber;
        this.money = money;
        this.accStatus = accStatus;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public AccStatus getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(AccStatus accStatus) {
        this.accStatus = accStatus;
    }

    public boolean isBlocked() {
        if (getAccStatus() == AccStatus.BLOCKED) {
        return true;
        } else {
        return false;
        }
    }
}

