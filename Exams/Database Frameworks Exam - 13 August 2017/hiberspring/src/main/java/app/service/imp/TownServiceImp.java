package app.service.imp;

import app.domain.dtos.json.importDtos.TownImportJSONDto;
import app.domain.dtos.xml.exportDtos.TownExportXml;
import app.domain.entities.Branch;
import app.domain.entities.Product;
import app.domain.entities.Town;
import app.repositories.TownRepository;
import app.service.api.TownService;
import app.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TownServiceImp implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImp(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void create(TownImportJSONDto townDto) {
        if (! ValidatorUtil.isValid(townDto)) {
            throw new IllegalArgumentException();
        }

        Town town = this.mapper.map(townDto, Town.class);
        this.townRepository.saveAndFlush(town);
    }

    @Override
    public Town findByName(String name) {
        return this.townRepository.findByName(name);
    }

    @Override
    public List<TownExportXml> getTownsInfo() {
        List<Town> allTowns = this.townRepository.findAll();

        List<TownExportXml> townsInfo = new ArrayList<>();

        for (Town town : allTowns) {
            TownExportXml townDto = new TownExportXml();
            townDto.setName(town.getName());
            townDto.setPopulation(town.getPopulation());
            Integer sumOfAllClients = 0;
            for (Branch branch : town.getBranches()) {
                for (Product product : branch.getProducts()) {
                    if (product.getBranch().getTown().getName().equals(town.getName())) {
                        sumOfAllClients += product.getClients();
                    }
                }
            }
            townDto.setTownClients(sumOfAllClients);
            townsInfo.add(townDto);
        }

        townsInfo.sort((x1, x2) -> x2.getTownClients().compareTo(x1.getTownClients()));

        return townsInfo;
    }
}
