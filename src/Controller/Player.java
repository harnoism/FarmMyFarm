package Controller;

public class Player {
    private int moneyLabel = 500;

    public int getMoney() {
        return moneyLabel;
    }

    public void addMoney(int value) {
        moneyLabel += value;
    }

    public void removeMoney(double value) {
        moneyLabel -= (int) value;
    }
}