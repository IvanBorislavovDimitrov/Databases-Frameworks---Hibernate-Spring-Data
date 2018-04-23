package org.softuni.mostwanted.services;

import org.softuni.mostwanted.entities.Town;
import org.softuni.mostwanted.entities.dto.json.exportDtos.RacingTownExportDtoJSON;
import org.softuni.mostwanted.entities.dto.json.importDtos.TownImportDtoJSON;

import java.util.List;

public interface TownService {

    void create(TownImportDtoJSON townDto);

    Town findByName(String name);

    List<RacingTownExportDtoJSON> getRacingTowns();
}
