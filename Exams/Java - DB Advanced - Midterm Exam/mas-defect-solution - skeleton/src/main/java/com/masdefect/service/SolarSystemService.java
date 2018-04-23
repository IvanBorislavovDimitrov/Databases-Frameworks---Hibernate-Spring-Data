package com.masdefect.service;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;

public interface SolarSystemService {

    void create(SolarSystemImportJSONDto solarSystemImportJSONDto);

    SolarSystem solarSystems(String name);
}
