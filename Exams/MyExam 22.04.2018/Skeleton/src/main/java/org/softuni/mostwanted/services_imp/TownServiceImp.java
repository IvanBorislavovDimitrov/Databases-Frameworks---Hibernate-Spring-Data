package org.softuni.mostwanted.services_imp;

import org.softuni.mostwanted.entities.Town;
import org.softuni.mostwanted.entities.dto.json.exportDtos.RacingTownExportDtoJSON;
import org.softuni.mostwanted.entities.dto.json.importDtos.TownImportDtoJSON;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TownServiceImp implements TownService {

    private final TownRepository townRepository;
    private final ModelParser mapper;

    @Autowired
    public TownServiceImp(TownRepository townRepository, ModelParser mapper) {
        this.townRepository = townRepository;
        this.mapper = mapper;
    }

    @Override
    public Town findByName(String name) {
        return this.townRepository.findByName(name);
    }

    @Override
    public List<RacingTownExportDtoJSON> getRacingTowns() {
        List<Town> towns = this.townRepository.findAll();
        List<RacingTownExportDtoJSON> racingTownExportDtoJSONS = new ArrayList<>();

        for (Town town : towns) {
            RacingTownExportDtoJSON racingTown = new RacingTownExportDtoJSON();
            racingTown.setName(town.getName());
            racingTown.setRacers(town.getRacers().size());
            racingTownExportDtoJSONS.add(racingTown);
        }

        racingTownExportDtoJSONS.sort((x1, x2) -> {
            if (x1.getRacers().equals(x2.getRacers())) {
                return x1.getName().compareTo(x2.getName());
            }

            return x2.getRacers().compareTo(x1.getRacers());
        });

        return racingTownExportDtoJSONS;
    }

    public void create(TownImportDtoJSON townDto) {
        if (!ValidationUtil.isValid(townDto)) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        Town town = this.townRepository.findByName(townDto.getName());
        if (town != null) {
            throw new IllegalArgumentException("Error: Duplicate Data");
        }

        town = this.mapper.convert(townDto, Town.class);
        this.townRepository.save(town);

    }
}
