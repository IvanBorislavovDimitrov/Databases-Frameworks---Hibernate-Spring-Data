package app.service.imp;

import app.domain.dtos.json.importDtos.BranchImportJSONDto;
import app.domain.dtos.xml.exportDtos.BranchExportXmlDto;
import app.domain.entities.Branch;
import app.domain.entities.Product;
import app.domain.entities.Town;
import app.repositories.BranchRepository;
import app.service.api.BranchService;
import app.service.api.TownService;
import app.validation.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BranchServiceImp implements BranchService {

    private final BranchRepository branchRepository;
    private final TownService townService;

    @Autowired
    public BranchServiceImp(BranchRepository branchRepository, TownService townService) {
        this.branchRepository = branchRepository;
        this.townService = townService;
    }

    @Override
    public void create(BranchImportJSONDto branchDto) {
        if (! ValidatorUtil.isValid(branchDto)) {
            throw new IllegalArgumentException();
        }

        Town town = this.townService.findByName(branchDto.getTownName());
        if (town == null) {
            throw new IllegalArgumentException();
        }
        Branch branch = new Branch();
        branch.setName(branchDto.getName());
        branch.setTown(town);

        this.branchRepository.saveAndFlush(branch);
    }

    @Override
    public Branch findByName(String name) {
        return this.branchRepository.findByName(name);
    }

    @Override
    public List<BranchExportXmlDto> topBranches() {
        List<Branch> branches = this.branchRepository.findAll();
        List<BranchExportXmlDto> branchesInfo = new ArrayList<>();

        for (Branch branch : branches) {
            BranchExportXmlDto branchDto = new BranchExportXmlDto();
            branchDto.setName(branch.getName());
            branchDto.setTown(branch.getTown().getName());
            Integer clients = 0;
            for (Product product : branch.getProducts()) {
                clients += product.getClients();
            }
            branchDto.setTotalClient(clients);
            branchesInfo.add(branchDto);
        }

        branchesInfo.sort((x1, x2) -> x2.getTotalClient().compareTo(x1.getTotalClient()));

        return branchesInfo;
    }
}
