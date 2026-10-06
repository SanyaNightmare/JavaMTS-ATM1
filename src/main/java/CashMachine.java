public class CashMachine implements WithdrawalOperations, DepositOperations {

    @Override
    public double deposit(double balance, Double amount) {
        if (amount == null || amount <= 0) {
            return balance;
        }

        return Math.round((balance + amount) * 100) / 100.0;
    }

    @Override
    public double withdraw(double balance, Double amount, BankType bankType) {

        double commission = applyCommission(amount, bankType);

        if (amount == null || amount <= 0) {
            return balance;
        }

        double total = amount + commission;

        if (total > balance) {
            System.out.println("Недостаточно средств.");
            return balance;
        }

        return Math.round((balance - total) * 100) / 100.0;
    }
}