package org.softuni.mostwanted.services_imp;

import org.softuni.mostwanted.entities.Car;
import org.softuni.mostwanted.entities.RaceEntry;
import org.softuni.mostwanted.entities.Racer;
import org.softuni.mostwanted.entities.Town;
import org.softuni.mostwanted.entities.dto.json.exportDtos.RacingCarsExportDtoJSON;
import org.softuni.mostwanted.entities.dto.json.importDtos.RacerImportDtoJSON;
import org.softuni.mostwanted.entities.dto.xml.exportDtos.EntryExportDtoXML;
import org.softuni.mostwanted.entities.dto.xml.exportDtos.MostWantedExportDtoXML;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.RacerService;
import org.softuni.mostwanted.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RacerServiceImp implements RacerService {

    private final RacerRepository racerRepository;
    private final TownService townService;
    private final ModelParser modelParser;

    @Autowired
    public RacerServiceImp(RacerRepository racerRepository, TownService townService,
                           ModelParser modelParser) {
        this.racerRepository = racerRepository;
        this.townService = townService;
        this.modelParser = modelParser;
    }

    @Override
    public void create(RacerImportDtoJSON racerDto) {
        if (!ValidationUtil.isValid(racerDto)) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        Town town = this.townService.findByName(racerDto.getTownName());
        if (town == null) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        Racer racer = this.modelParser.convert(racerDto, Racer.class);
        racer.setHomeTown(town);
        this.racerRepository.save(racer);
    }

    @Override
    public Racer findByName(String name) {
        return this.racerRepository.findByName(name);
    }

    @Override
    public List<RacingCarsExportDtoJSON> racingCars() {
        List<Racer> racers = this.racerRepository.findAll();
        List<RacingCarsExportDtoJSON> raceCars = new ArrayList<>();
        for (Racer racer : racers) {
            RacingCarsExportDtoJSON racerInfo = new RacingCarsExportDtoJSON();
            racerInfo.setName(racer.getName());
            racerInfo.setAge(racer.getAge());
            List<Car> racerCars = racer.getCars();
            racerCars.sort((x1, x2) -> x2.getId().compareTo(x1.getId()));
            for (Car car : racerCars) {
                racerInfo.getCars().add(String.format("%s %s %s", car.getBrand(), car.getModel(), car.getYearOfProduction()));
            }
            raceCars.add(racerInfo);
        }

        raceCars.sort((x1, x2) -> {
            if (x1.getCars().size() == x2.getCars().size()) {
                return x1.getName().compareTo(x2.getName());
            }

            return Integer.compare(x2.getCars().size(), x1.getCars().size());
        });

        return raceCars;
    }

    @Override
    public MostWantedExportDtoXML getMostWanted() {
        MostWantedExportDtoXML mostWantedExportDtoXML = new MostWantedExportDtoXML();
        List<Racer> racers = this.racerRepository.findAll();
        int maxEntries = 0;
        Racer mostWantedRacer = racers.get(0);
        for (Racer racer : racers) {
            if (racer.getRaceEntries().size() > maxEntries) {
                maxEntries = racer.getRaceEntries().size();
                mostWantedRacer = racer;
            }
        }
        mostWantedExportDtoXML.getRacer().setName(mostWantedRacer.getName());

        for (RaceEntry raceEntry: mostWantedRacer.getRaceEntries()) {
            EntryExportDtoXML entryExportDtoXML = new EntryExportDtoXML();
            entryExportDtoXML.setCar(String.format("%s %s @ %s", raceEntry.getCar().getBrand(),
                    raceEntry.getCar().getModel(), raceEntry.getCar().getYearOfProduction()));
            entryExportDtoXML.setFinishTime(raceEntry.getFinishTime());
            mostWantedExportDtoXML.getRacer().getEntries().add(entryExportDtoXML);
        }

        mostWantedExportDtoXML.getRacer().getEntries().sort((x1, x2) -> x1.getFinishTime().compareTo(x2.getFinishTime()));

        return mostWantedExportDtoXML;
    }
}
