package ObjectOrientedPrograming.ExerciceOne;

// Payments and Transactions processing
public class Account {
    private int id;
    private Transaction[] transactions;

    public Account(int id) {
        this.id = id;
        this.transactions = new Transaction[0];
    }

    public int getId() {
        return id;
    }

    public void sendMoneyToAccount(Account accountTo, double moneyAmount) {
        if (accountTo == null) {
            throw new IllegalArgumentException("accountTo cannot be null");
        }
        if (moneyAmount <= 0) {
            throw new IllegalArgumentException("moneyAmount must be positive");
        }

        Transaction sendTx = new Transaction(this, accountTo, moneyAmount, StandardAccountOperations.MONEY_TRANSFER_SEND);
        appendTransaction(sendTx);

        Transaction receiveTx = new Transaction(this, accountTo, moneyAmount, StandardAccountOperations.MONEY_TRANSFER_RECEIVE);
        accountTo.appendTransaction(receiveTx);
    }

    public void withdrawMoney(double moneyAmount) {
        if (moneyAmount <= 0) {
            throw new IllegalArgumentException("moneyAmount must be positive");
        }
        Transaction tx = new Transaction(this, null, moneyAmount, StandardAccountOperations.WITHDRAW);
        appendTransaction(tx);
    }

    public Transaction[] getTransactions() {
        Transaction[] copy = new Transaction[this.transactions.length];
        System.arraycopy(this.transactions, 0, copy, 0, this.transactions.length);
        return copy;
    }

    private void appendTransaction(Transaction tx) {
        Transaction[] arr = new Transaction[this.transactions.length + 1];
        System.arraycopy(this.transactions, 0, arr, 0, this.transactions.length);
        arr[arr.length - 1] = tx;
        this.transactions = arr;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + '}';
    }

    public static class Transaction {
        public final Account accountFrom;
        public final Account accountTo;
        public final double moneyAmount;
        public final StandardAccountOperations operation;

        public Transaction(Account accountFrom, Account accountTo, double moneyAmount, StandardAccountOperations operation) {
            this.accountFrom = accountFrom;
            this.accountTo = accountTo;
            this.moneyAmount = moneyAmount;
            this.operation = operation;
        }

        @Override
        public String toString() {
            switch (operation) {
                case MONEY_TRANSFER_SEND:
                    return String.format("[%s] %s -> %s : $%.2f", operation, accountFrom, accountTo, moneyAmount);
                case MONEY_TRANSFER_RECEIVE:
                    return String.format("[%s] %s <- %s : $%.2f", operation, accountTo, accountFrom, moneyAmount);
                case WITHDRAW:
                default:
                    return String.format("[%s] %s : $%.2f", operation, accountFrom, moneyAmount);
            }
        }
    }
}

enum StandardAccountOperations {
    MONEY_TRANSFER_SEND,
    MONEY_TRANSFER_RECEIVE,
    WITHDRAW
}
