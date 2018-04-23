package com.masdefect.servicesImp;

import com.masdefect.domain.dto.json.PersonExportJSONDto;
import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.service.PersonService;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

    private PersonRepository personRepository;
    private PlanetRepository planetRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository, PlanetRepository planetRepository) {
        this.personRepository = personRepository;
        this.planetRepository = planetRepository;
    }

    @Override
    public void create(PersonImportJSONDto personImportJSONDto) {
        if (! ValidationUtil.isValid(personImportJSONDto)) {
            throw new IllegalArgumentException();
        }

        Planet planet = this.planetRepository.findByName(personImportJSONDto.getHomePlanet());
        if (planet == null) {
            throw new IllegalArgumentException();
        }

        Person person = new Person();
        person.setName(personImportJSONDto.getName());
        person.setHomePlanet(planet);

        this.personRepository.saveAndFlush(person);
    }

    @Override
    public List<PersonExportJSONDto> findInnocentPersons() {
        return null;
    }
}
