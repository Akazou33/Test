package ObjectOrientedPrograming.ExerciceOne;

public class PaymentsApp {
    public static void main(String[] args) {
        Account a1 = new Account(1);
        Account a2 = new Account(2);
        Account a3 = new Account(3);

        a1.sendMoneyToAccount(a2, 50.0);
        a2.sendMoneyToAccount(a3, 30.25);
        a3.withdrawMoney(10.0);
        a1.withdrawMoney(5.5);

        printAccountTransactions(a1);
        printAccountTransactions(a2);
        printAccountTransactions(a3);
    }

    private static void printAccountTransactions(Account a) {
        System.out.println("Transactions for " + a);
        for (Account.Transaction t : a.getTransactions()) {
            System.out.println("  " + t);
        }
        System.out.println();
    }
}
