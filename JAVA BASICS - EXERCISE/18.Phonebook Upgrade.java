import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<String, String> contacts = new TreeMap<>();

        String line;
        while (! (line = input.nextLine()).equals("END")) {
            String[] infoAboutContact = line.split("\\s+");
            switch (infoAboutContact[0]) {
                case "A":
                    String name = infoAboutContact[1];
                    String phoneNumber = infoAboutContact[2];
                    contacts.put(name, phoneNumber);
                    break;
                case "S":
                    name = infoAboutContact[1];
                    if (contacts.containsKey(name)) {
                        System.out.println(name + " -> " + contacts.get(name));
                    } else {
                        System.out.printf("Contact %s does not exist.%n", name);
                    }
                    break;
                case "ListAll":
                    for (Map.Entry<String, String> contact : contacts.entrySet()) {
                        System.out.println(contact.getKey() + " -> " + contact.getValue());
                    }
                    break;
            }
        }
    }
}
