public interface WithdrawalOperations {

    double withdraw(double balance, Double amount, BankType bankType);

    default double applyCommission(Double amount, BankType bankType) {
        if (amount == null || bankType == null) {
            return 0.00;
        }

        double commission = amount * bankType.getCommission();

        return Math.round(commission * 100) / 100.0;
    }
}