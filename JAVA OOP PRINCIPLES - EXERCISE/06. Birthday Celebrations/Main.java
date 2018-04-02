package border_control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<Entry> entries = new ArrayList<>();
        String line;

        while (! (line = input.readLine()).equals("End")) {
            String[] infoAboutEntry = line.split("\\s+");
            switch (infoAboutEntry[0]) {
                case "Citizen":
                    Entry entry = new Person(infoAboutEntry[1], Integer.parseInt(infoAboutEntry[2]), infoAboutEntry[3],
                            infoAboutEntry[4]);
                    entries.add(entry);
                    break;
                case "Pet":
                    entry = new Pet(infoAboutEntry[1], infoAboutEntry[2]);
                    entries.add(entry);
                    break;
                case "Robot":
                    entry = new Robot(infoAboutEntry[1], infoAboutEntry[2]);
                    entries.add(entry);
                    break;
            }
        }

        String year = input.readLine();

        for (Entry entry : entries) {
            if (entry.getBirthdate() != null && entry.getBirthdate().endsWith(year)) {
                System.out.println(entry.getBirthdate());
            }
        }
    }
}
