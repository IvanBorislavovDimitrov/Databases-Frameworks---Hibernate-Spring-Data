package org.softuni.mostwanted.services_imp;

import org.softuni.mostwanted.entities.Car;
import org.softuni.mostwanted.entities.RaceEntry;
import org.softuni.mostwanted.entities.Racer;
import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceEntryImportDtoXML;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.services.CarService;
import org.softuni.mostwanted.services.RacerEntryService;
import org.softuni.mostwanted.services.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RacerEntryServiceImp implements RacerEntryService {

    private final RaceEntryRepository raceEntryRepository;
    private final RacerService racerService;
    private final ModelParser modelParser;
    private final CarService carService;

    @Autowired
    public RacerEntryServiceImp(RaceEntryRepository raceEntryRepository, RacerService racerService, ModelParser modelParser, CarService carService) {
        this.raceEntryRepository = raceEntryRepository;
        this.racerService = racerService;
        this.modelParser = modelParser;
        this.carService = carService;
    }

    @Override
    public Integer create(RaceEntryImportDtoXML raceEntryDto) {
        if (!ValidationUtil.isValid(raceEntryDto)) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        Racer racer = this.racerService.findByName(raceEntryDto.getRacer());
        Car car = this.carService.findById(raceEntryDto.getCarId());

        if (racer == null || car == null) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        RaceEntry raceEntry = this.modelParser.convert(raceEntryDto, RaceEntry.class);
        raceEntry.setId(null);
        raceEntry.setRace(null);

        raceEntry.setRacer(racer);
        raceEntry.setCar(car);

        this.raceEntryRepository.save(raceEntry);

        return raceEntry.getId();
    }

    @Override
    public RaceEntry findById(Integer id) {
        return this.raceEntryRepository.findOne(id);
    }

    @Override
    public void save(RaceEntry raceEntry) {
        this.raceEntryRepository.save(raceEntry);
    }
}
