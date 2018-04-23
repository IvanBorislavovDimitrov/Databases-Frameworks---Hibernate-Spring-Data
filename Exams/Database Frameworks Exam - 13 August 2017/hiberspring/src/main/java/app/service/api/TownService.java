package app.service.api;

import app.domain.dtos.json.importDtos.TownImportJSONDto;
import app.domain.dtos.xml.exportDtos.TownExportXml;
import app.domain.entities.Town;

import java.util.List;

public interface TownService {

    void create(TownImportJSONDto townDto);

    Town findByName(String name);

    List<TownExportXml> getTownsInfo();
}
