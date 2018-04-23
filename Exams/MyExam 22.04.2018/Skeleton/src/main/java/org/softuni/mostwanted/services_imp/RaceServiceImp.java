package org.softuni.mostwanted.services_imp;

import org.softuni.mostwanted.entities.District;
import org.softuni.mostwanted.entities.Race;
import org.softuni.mostwanted.entities.RaceEntry;
import org.softuni.mostwanted.entities.dto.xml.importDtos.EntryImportDtoXML;
import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceImportDtoXML;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.DistrictService;
import org.softuni.mostwanted.services.RaceService;
import org.softuni.mostwanted.services.RacerEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RaceServiceImp implements RaceService {

    private final RaceRepository raceRepository;
    private final DistrictService districtService;
    private final RacerEntryService racerEntryService;


    @Autowired
    public RaceServiceImp(RaceRepository raceRepository, DistrictService districtService, RacerEntryService racerEntryService) {
        this.raceRepository = raceRepository;
        this.districtService = districtService;
        this.racerEntryService = racerEntryService;
    }

    @Override
    public Integer create(RaceImportDtoXML raceDto) {
        if (!ValidationUtil.isValid(raceDto)) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        District district = this.districtService.findByName(raceDto.getDistrictName());
        if (district == null) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        Race race = new Race();
        race.setDistrict(district);
        race.setLaps(raceDto.getLaps());

        for (EntryImportDtoXML entryImportDtoXML : raceDto.getEntries()) {
            RaceEntry raceEntry = this.racerEntryService.findById(entryImportDtoXML.getId());
            race.getEntries().add(raceEntry);
            raceEntry.setRace(race);
        }

        this.raceRepository.save(race);

        return race.getId();
    }

}
