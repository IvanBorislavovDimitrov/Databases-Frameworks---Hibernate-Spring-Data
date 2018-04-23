package com.masdefect.servicesImp;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SolarSystemServiceImp implements SolarSystemService {

    private final SolarSystemRepository solarSystemRepository;
    private final ModelParser mapper;

    @Autowired
    public SolarSystemServiceImp(SolarSystemRepository solarSystemRepository, ModelParser mapper) {
        this.solarSystemRepository = solarSystemRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(SolarSystemImportJSONDto solarSystemImportJSONDto) {
        if (! ValidationUtil.isValid(solarSystemImportJSONDto)) {
            throw new IllegalArgumentException();
        }

        SolarSystem solarSystem = this.mapper.convert(solarSystemImportJSONDto, SolarSystem.class);
        this.solarSystemRepository.saveAndFlush(solarSystem);
    }

    @Override
    public SolarSystem solarSystems(String name) {
        return this.solarSystemRepository.findByName(name);
    }
}
