package com.masdefect.servicesImp;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.domain.entities.Planet;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlanetServiceImp implements PlanetService {

    private StarRepository starRepository;
    private SolarSystemRepository solarSystemRepository;
    private PlanetRepository planetRepository;

    @Autowired
    public PlanetServiceImp(StarRepository starRepository, SolarSystemRepository solarSystemRepository, PlanetRepository planetRepository) {
        this.starRepository = starRepository;
        this.solarSystemRepository = solarSystemRepository;
        this.planetRepository = planetRepository;
    }

    @Override
    public void create(PlanetImportJSONDto planetImportJSONDto) {
        if (!ValidationUtil.isValid(planetImportJSONDto)) {
            throw new IllegalArgumentException();
        }
        Star star  = this.starRepository.findByName(planetImportJSONDto.getSun());
        if (star == null) {
            throw new IllegalArgumentException();
        }
        SolarSystem solarSystem = this.solarSystemRepository.findByName(planetImportJSONDto.getSolarSystem());
        if (solarSystem == null) {
            throw new IllegalArgumentException();
        }

        Planet planet = new Planet();
        planet.setName(planetImportJSONDto.getName());
        planet.setSun(star);
        planet.setSolarSystem(solarSystem);
        this.planetRepository.saveAndFlush(planet);
    }

    @Override
    public List<PlanetExportJSONDto> findAllPlanetsWithoutPeopleTeleportedFromThem() {
        List<Planet> planetExportJSONDtos = this.planetRepository.planetNotInTeleports();
        List<PlanetExportJSONDto> planetExportJSONDtos1 = new ArrayList<>();
        for (Planet planet : planetExportJSONDtos) {
            PlanetExportJSONDto planetExportJSONDto = new PlanetExportJSONDto();
            planetExportJSONDto.setName(planet.getName());
            planetExportJSONDtos1.add(planetExportJSONDto);
        }

        return planetExportJSONDtos1;
    }
}
