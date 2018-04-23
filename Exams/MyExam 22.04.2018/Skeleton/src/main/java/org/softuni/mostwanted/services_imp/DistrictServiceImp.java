package org.softuni.mostwanted.services_imp;

import org.softuni.mostwanted.entities.District;
import org.softuni.mostwanted.entities.Town;
import org.softuni.mostwanted.entities.dto.json.importDtos.DistrictImportDtoJSON;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.services.DistrictService;
import org.softuni.mostwanted.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImp implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ModelParser modelParser;
    private final TownService townService;

    @Autowired
    public DistrictServiceImp(DistrictRepository districtRepository, ModelParser modelParser, TownService townService) {
        this.districtRepository = districtRepository;
        this.modelParser = modelParser;
        this.townService = townService;
    }

    @Override
    public void create(DistrictImportDtoJSON districtDto) {
        if (!ValidationUtil.isValid(districtDto)) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        Town town = this.townService.findByName(districtDto.getTownName());
        if (town == null) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        District district = this.districtRepository.findByName(districtDto.getName());

        if (district != null) {
            throw new IllegalArgumentException("Error: Duplicate Data");
        }

        district = this.modelParser.convert(districtDto, District.class);
        district.setTown(town);

        this.districtRepository.save(district);
    }

    @Override
    public District findByName(String name) {
        return this.districtRepository.findByName(name);
    }
}
