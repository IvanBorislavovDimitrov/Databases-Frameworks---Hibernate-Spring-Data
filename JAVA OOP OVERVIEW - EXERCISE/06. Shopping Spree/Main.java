package shoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Person> people = getPeople(input.nextLine());
        if (people == null) {
            return;
        }
        List<Product> products = getProducts(input.nextLine());

        if (products == null) {
            return;
        }

        String line;
        while (! (line = input.nextLine()).equals("END")) {
            String[] boughtProduct = line.split("\\s");
            String buyerName = boughtProduct[0];
            String productName = boughtProduct[1];

            Product product = null;
            Person person = null;

            for (Product currentProduct : products) {
                if (currentProduct.getName().equals(productName)) {
                    product = currentProduct;
                    break;
                }
            }

            for (Person currentProduct : people) {
                if (currentProduct.getName().equals(buyerName)) {
                    person = currentProduct;
                    break;
                }
            }

            if (person.getMoney() - product.getCost() < 0) {
                System.out.println(String.format("%s can't afford %s", buyerName, productName));
                continue;
            }

            person.setMoney(person.getMoney() - product.getCost());
            person.getBagOfProducts().add(product);
            System.out.println(String.format("%s bought %s", buyerName, productName));
        }

        for (Person person : people) {
            if (!person.getBagOfProducts().isEmpty()) {
                System.out.print(String.format("%s - ", person.getName()));
                StringBuilder sb = new StringBuilder();
                for (Product product : person.getBagOfProducts()) {
                    sb.append(product.getName()).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                System.out.println(sb);
            } else {
                System.out.println(String.format("%s - %s", person.getName(),
                        String.join(" ,", "Nothing bought")));
            }
        }
    }

    private static List<Person> getPeople(String line) {
        try {
            List<Person> people = new ArrayList<>();
            String[] infoAboutPeople = line.split(";+");
            for (String currPerson : infoAboutPeople) {
                String[] infoAboutPerson = currPerson.split("=+");
                Person person = new Person(infoAboutPerson[0], Double.parseDouble(infoAboutPerson[1]));
                people.add(person);
            }
            return people;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static List<Product> getProducts(String line) {
        try {
            List<Product> products = new ArrayList<>();
            String[] infoAboutProducts = line.split(";");
            for (String currProduct : infoAboutProducts) {
                String[] infoAboutProduct = currProduct.split("=+");
                Product product = new Product(infoAboutProduct[0], Double.parseDouble(infoAboutProduct[1]));
                products.add(product);
            }

            return products;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
