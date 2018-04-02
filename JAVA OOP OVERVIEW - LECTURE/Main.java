import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<Integer, BankAccount1> accounts = new HashMap<>();

        String line;
        while (! (line = input.nextLine()).equals("End")) {
            String[] infoAboutLine = line.split("\\s+");
            String command = infoAboutLine[0];
            int id = Integer.parseInt(infoAboutLine[1]);

            switch (command) {
                case "Create": {
                    createAccount(accounts, id);
                    break;
                }
                case "Deposit": {
                    double deposit = Double.parseDouble(infoAboutLine[2]);
                    makeDeposit(accounts, id, deposit);
                    break;
                }
                case "Withdraw": {
                    double amount = Double.parseDouble(infoAboutLine[2]);
                    withdraw(accounts, id, amount);
                    break;
                }
                case "Print": {
                    print(accounts, id);
                    break;
                }
            }
        }
    }

    private static void print(HashMap<Integer, BankAccount1> accounts, int id) {
        if (accounts.containsKey(id)) {
            System.out.printf("Account ID%d, balance %.2f%n",id,  accounts.get(id).getBalance());
        } else {
            System.out.println("Account does not exist");
        }
    }

    private static void withdraw(HashMap<Integer, BankAccount1> accounts, int id, double amount) {
        if (accounts.containsKey(id)) {
            double currentBalance =  accounts.get(id).getBalance() - amount;
            if (currentBalance >= 0) {
                accounts.get(id).setBalance(currentBalance);
            } else {
                System.out.println("Insufficient balance");
            }
        } else {
            System.out.println("Account does not exist");
        }
    }

    private static void makeDeposit(HashMap<Integer, BankAccount1> accounts, int id, double amount) {
        if (accounts.containsKey(id)) {
            accounts.get(id).setBalance(accounts.get(id).getBalance() + amount);
        } else {
            System.out.println("Account does not exist");
        }
    }

    private static void createAccount(HashMap<Integer, BankAccount1> accounts, int id) {
        if (! accounts.containsKey(id)) {
            BankAccount1 bankAccount = new BankAccount1();
            bankAccount.setId(id);
            bankAccount.setBalance(0);
            accounts.put(id, bankAccount);
        } else {
            System.out.println("Account already exists");
        }
    }

    public static class BankAccountMain {

        public static void main(String[] args) {
            BankAccount1 bankAccount = new BankAccount1();
            bankAccount.setBalance(121212.12);
            bankAccount.setId(1);
            System.out.printf("balance - %f, id - %d%n", bankAccount.getBalance(), bankAccount.getId());
        }
    }
}
