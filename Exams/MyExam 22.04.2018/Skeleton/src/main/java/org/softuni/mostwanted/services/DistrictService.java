package org.softuni.mostwanted.services;

import org.softuni.mostwanted.entities.District;
import org.softuni.mostwanted.entities.dto.json.importDtos.DistrictImportDtoJSON;

public interface DistrictService {

    void create(DistrictImportDtoJSON districtDto);

    District findByName(String name);
}
