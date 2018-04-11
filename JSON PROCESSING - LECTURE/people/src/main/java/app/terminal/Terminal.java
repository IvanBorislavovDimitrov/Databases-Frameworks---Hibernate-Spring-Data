package app.terminal;

import app.domain.dto.PersonDto;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private static final String PERSON_INPUT_JSON = "C:\\Users\\Ivan\\Desktop\\SoftUni\\Java DB Fundamentals\\Databases Frameworks - Hibernate & Spring Data\\JSON PROCESSING - LECTURE\\people\\src\\main\\resources\\files\\input\\json\\person.json";
    private static final String PERSONS_INPUT_JSON = "C:\\Users\\Ivan\\Desktop\\SoftUni\\Java DB Fundamentals\\Databases Frameworks - Hibernate & Spring Data\\JSON PROCESSING - LECTURE\\people\\src\\main\\resources\\files\\input\\json\\persons.json";
    private static final String PERSONS_OUTPUT_JSON = "C:\\Users\\Ivan\\Desktop\\SoftUni\\Java DB Fundamentals\\Databases Frameworks - Hibernate & Spring Data\\JSON PROCESSING - LECTURE\\people\\src\\main\\resources\\files\\input\\json\\person_export.json";
    private static final String PERSONS1_OUTPUT_JSON = "C:\\Users\\Ivan\\Desktop\\SoftUni\\Java DB Fundamentals\\Databases Frameworks - Hibernate & Spring Data\\JSON PROCESSING - LECTURE\\people\\src\\main\\resources\\files\\input\\json\\persons_export.json";
    private final PersonService personService;

    @Autowired
    public Terminal(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... strings) throws Exception {
        importPersonJSON();
        importPersonsJSON();
        exportPerson();
        exportPeople();
    }

    private void exportPeople() {
        List<PersonDto> peopleDtos = this.personService.findByCountry("Bulgaria");
        try {
            this.personService.exportPeople(peopleDtos, PERSONS1_OUTPUT_JSON);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exportPerson() {
        PersonDto personDto = this.personService.findById(1L);
        try {
            this.personService.exportPerson(personDto, PERSONS_OUTPUT_JSON);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void importPersonsJSON() {
        this.personService.importPeople(PERSONS_INPUT_JSON);
    }

    private void importPersonJSON() {
        this.personService.importOnePerson(PERSON_INPUT_JSON);
    }
}
