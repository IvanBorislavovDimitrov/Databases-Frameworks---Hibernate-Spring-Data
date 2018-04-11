package app.service;

import app.domain.dto.PersonDto;
import app.domain.model.Person;

import java.io.FileNotFoundException;
import java.util.List;

public interface PersonService {


    PersonDto findById(long id);

    List<PersonDto> findByCountry(String country);

    void importOnePerson(String fileName);

    void importPeople(String fileName);

    void exportPerson(PersonDto personDto, String fileName) throws FileNotFoundException;

    void exportPeople(List<PersonDto> personDtos, String fileName) throws FileNotFoundException;
}
