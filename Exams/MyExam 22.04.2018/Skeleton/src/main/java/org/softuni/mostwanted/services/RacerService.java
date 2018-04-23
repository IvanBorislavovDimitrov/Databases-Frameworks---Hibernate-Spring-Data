package org.softuni.mostwanted.services;

import org.softuni.mostwanted.entities.Racer;
import org.softuni.mostwanted.entities.dto.json.exportDtos.RacingCarsExportDtoJSON;
import org.softuni.mostwanted.entities.dto.json.importDtos.RacerImportDtoJSON;
import org.softuni.mostwanted.entities.dto.xml.exportDtos.MostWantedExportDtoXML;

import java.util.List;

public interface RacerService {

    void create(RacerImportDtoJSON racerDto);

    Racer findByName(String name);

    List<RacingCarsExportDtoJSON> racingCars();

    MostWantedExportDtoXML getMostWanted();

}
