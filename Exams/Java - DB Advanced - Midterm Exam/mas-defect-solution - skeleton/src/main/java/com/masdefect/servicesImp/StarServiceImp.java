package com.masdefect.servicesImp;

import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.StarRepository;
import com.masdefect.service.SolarSystemService;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StarServiceImp implements StarService {

    private final StarRepository starRepository;
    private final SolarSystemService solarSystemService;

    @Autowired
    public StarServiceImp(StarRepository starRepository, SolarSystemService solarSystemService) {
        this.starRepository = starRepository;
        this.solarSystemService = solarSystemService;
    }

    @Override
    public void create(StarImportJSONDto starImportJSONDto) {
        if (! ValidationUtil.isValid(starImportJSONDto)) {
            throw new IllegalArgumentException();
        }

        SolarSystem solarSystem = this.solarSystemService.solarSystems(starImportJSONDto.getSolarSystem());

        Star star = new Star();
        star.setName(starImportJSONDto.getName());
        star.setSolarSystem(solarSystem);

        this.starRepository.saveAndFlush(star);
    }
}
