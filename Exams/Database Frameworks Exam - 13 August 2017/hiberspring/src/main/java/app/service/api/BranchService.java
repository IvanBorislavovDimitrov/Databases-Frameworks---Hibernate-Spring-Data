package app.service.api;

import app.domain.dtos.json.importDtos.BranchImportJSONDto;
import app.domain.dtos.xml.exportDtos.BranchExportXmlDto;
import app.domain.entities.Branch;

import java.util.List;

public interface BranchService {

    void create(BranchImportJSONDto branchDto);

    Branch findByName(String name);

    List<BranchExportXmlDto> topBranches();
}
