package telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String phoneNumbers = input.readLine();
        String urls = input.readLine();

        Smartphone smartphone = new MySmartphone();
        smartphone.call(phoneNumbers);
        smartphone.browseInWww(urls);
    }
}
