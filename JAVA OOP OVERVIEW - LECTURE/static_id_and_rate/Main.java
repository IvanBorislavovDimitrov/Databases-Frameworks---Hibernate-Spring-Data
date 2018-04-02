package static_id_and_rate;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<BankAccount> bankAccounts = new ArrayList<>();
        String line;
        while (! (line = input.nextLine()).equals("End")) {
            String[] infoAboutLine = line.split("\\s+");
            String command = infoAboutLine[0];

            switch (command) {
                case "Create": {
                    createAccount(bankAccounts);
                    break;
                }

                case "Deposit": {
                    int id = Integer.parseInt(infoAboutLine[1]);
                    double deposit = Double.parseDouble(infoAboutLine[2]);
                    deposit(bankAccounts, id, deposit);
                    break;
                }

                case "SetInterest": {
                    double interest = Double.parseDouble(infoAboutLine[1]);
                    setInterest(interest);
                    break;
                }

                case "GetInterest": {
                    int id = Integer.parseInt(infoAboutLine[1]);
                    int years = Integer.parseInt(infoAboutLine[2]);
                    getInterest(bankAccounts, id, years);
                    break;
                }
            }
        }
    }

    private static void getInterest(List<BankAccount> bankAccounts, int id, int years) {
        List<BankAccount> bankAccs = bankAccounts.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
        if (bankAccs.size() != 0) {
            BankAccount bankAccount = bankAccs.get(0);
            System.out.printf("%.2f%n", bankAccount.getInterestRate(years));
        } else {
            System.out.println("Account does not exist");
        }
    }

    private static void setInterest(double interest) {
            BankAccount.setInterestRate(interest);
        }

    private static void deposit(List<BankAccount> bankAccounts, int id, double deposit) {
        List<BankAccount> bankAccs = bankAccounts.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
        if (bankAccs.size() != 0) {
            BankAccount bankAccount = bankAccs.get(0);
            bankAccount.deposit(deposit);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            System.out.printf("Deposited %s to ID%d%n", decimalFormat.format(deposit), id);
        } else {
            System.out.println("Account does not exist");
        }
    }

    private static void createAccount(List<BankAccount> bankAccounts) {
        BankAccount bankAccount = new BankAccount();
        bankAccounts.add(bankAccount);
        System.out.printf("Account ID%d created%n", bankAccount.getId());
    }
}
