package static_id_and_rate;

public class BankAccount {

    public static final double DEFAULT_INTEREST = 0.002d;
    private static int countId = 1;
    private double balance;
    private static double interestRate = DEFAULT_INTEREST;
    private int id;

    public BankAccount() {
        this.id = countId++;
        this.balance = 0;
    }

    public int getId() {
        return id;
    }

    public static void setInterestRate(double interest) {
        interestRate = interest;
    }

    public double getInterestRate(int years) {
        return this.balance * interestRate * years;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
