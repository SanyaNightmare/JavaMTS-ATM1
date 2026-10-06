public class Account {

    public int cardNumber;
    public int pinCode;
    public double balance;
    public BankType bankType;

    public Account(int cardNumber, int pinCode, double balance, BankType bankType) {

        if (cardNumber < 10000 || cardNumber > 99999) {
            cardNumber = 10000;
        }

        if (pinCode < 100 || pinCode > 999) {
            pinCode = 100;
        }

        if (balance < 0) {
            balance = 0.00;
        }

        if (bankType == null) {
            bankType = BankType.NEO;
        }

        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = Math.round(balance * 100) / 100.0;
        this.bankType = bankType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public double getBalance() {
        return balance;
    }

    public BankType getBankType() {
        return bankType;
    }

    @Override
    public String toString() {
        return bankType.getName()
                + " Карта: " + cardNumber
                + ", Баланс: " + String.format("%.2f", balance)
                + " руб.";
    }
}