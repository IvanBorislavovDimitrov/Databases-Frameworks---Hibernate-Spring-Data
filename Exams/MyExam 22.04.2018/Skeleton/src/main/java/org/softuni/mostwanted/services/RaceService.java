package org.softuni.mostwanted.services;

import org.softuni.mostwanted.entities.dto.json.exportDtos.RacingCarsExportDtoJSON;
import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceImportDtoXML;

import java.util.List;

public interface RaceService {

    Integer create(RaceImportDtoXML raceDto);


}
