package org.softuni.mostwanted.services;


import org.softuni.mostwanted.entities.RaceEntry;
import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceEntryImportDtoXML;

public interface RacerEntryService {

    Integer create(RaceEntryImportDtoXML raceEntryDto);

    RaceEntry findById(Integer id);

    void save(RaceEntry raceEntry);
}
