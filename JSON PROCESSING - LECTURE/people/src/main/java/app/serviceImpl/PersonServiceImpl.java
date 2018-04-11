package app.serviceImpl;

import app.domain.dto.AddressDto;
import app.domain.dto.PersonDto;
import app.domain.dto.PhoneNumberDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.io.FileIO;
import app.repository.PersonRepository;
import app.serialize.JsonSerializer;
import app.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final JsonSerializer serializer;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, JsonSerializer serializer) {
        this.personRepository = personRepository;
        this.serializer = serializer;
    }

    @Override
    public PersonDto findById(long id) {
        Person person = this.personRepository.findById(id).get();
        PersonDto personDto = this.mapper.map(person, PersonDto.class);
        personDto.setPhoneJsonDtos(new ArrayList<>());
        for (PhoneNumber phoneNumber : person.getPhoneNumbers()) {
            personDto.getPhoneJsonDtos().add(this.mapper.map(phoneNumber, PhoneNumberDto.class));
        }
        personDto.setAddressImportDto(this.mapper.map(person.getAddress(), AddressDto.class));

        return personDto;
    }

    @Override
    public List<PersonDto> findByCountry(String country) {
        List<Person> people =  this.personRepository.findByCountry(country);
        List<PersonDto> personDtos = new ArrayList<>();
        for (Person person : people) {
            PersonDto personDto = this.mapper.map(person, PersonDto.class);
            personDto.setPhoneJsonDtos(new ArrayList<>());
            for (PhoneNumber phoneNumber : person.getPhoneNumbers()) {
                PhoneNumberDto phoneNumberDto = this.mapper.map(phoneNumber, PhoneNumberDto.class);
                personDto.getPhoneJsonDtos().add(phoneNumberDto);
            }
            personDto.setAddressImportDto(this.mapper.map(person.getAddress(), AddressDto.class));
            personDtos.add(personDto);
        }
        return personDtos;
    }

    @Override
    public void importOnePerson(String fileName) {
        PersonDto personDto = this.serializer.deserialize(PersonDto.class, fileName);
        importOne(personDto);
    }

    private void importOne(PersonDto personDto) {
        Person person = this.mapper.map(personDto, Person.class);
        for (PhoneNumberDto phoneNumberDto : personDto.getPhoneJsonDtos()) {
            person.getPhoneNumbers().add(this.mapper.map(phoneNumberDto, PhoneNumber.class));
        }
        for (PhoneNumber phoneNumber : person.getPhoneNumbers()) {
            phoneNumber.setPerson(person);
        }
        this.personRepository.save(person);
    }

    @Override
    public void importPeople(String fileName) {
        PersonDto[] personDtos = this.serializer.deserialize(PersonDto[].class, fileName);
        for (PersonDto personDto : personDtos) {
            importOne(personDto);
        }
    }

    @Override
    public void exportPerson(PersonDto personDto, String fileName) throws FileNotFoundException {
        String json = this.serializer.serialize(personDto);
        FileIO.writeFile(json, fileName);
    }

    @Override
    public void exportPeople(List<PersonDto> personDtos, String fileName) throws FileNotFoundException {
        String json = this.serializer.serialize(personDtos);
        FileIO.writeFile(json, fileName);
    }
}
