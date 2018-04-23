package app.controllers;

import app.domain.dtos.json.importDtos.BranchImportJSONDto;
import app.domain.dtos.xml.exportDtos.BranchExportWrapperXmlDto;
import app.parsers.JsonParser;
import app.parsers.XmlParser;
import app.service.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BranchController {

    private final BranchService branchService;
    private final JsonParser jsonParser;
    private final XmlParser xmlParser;

    @Autowired
    public BranchController(BranchService branchService, JsonParser jsonParser, XmlParser xmlParser) {
        this.branchService = branchService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importBranchesFromJSON(String fileContent) {
        StringBuilder sb = new StringBuilder();
        BranchImportJSONDto[] branches = this.jsonParser.read(BranchImportJSONDto[].class, fileContent);

        for (BranchImportJSONDto branch : branches) {
            try {
                this.branchService.create(branch);
                sb.append(String.format("Branch %s has been added.\r\n", branch.getName()));
            } catch (IllegalArgumentException e) {
                sb.append("Error: Invalid data.\r\n");
            }
        }

        return sb.toString();
    }

    public String topBranches() {
        BranchExportWrapperXmlDto branchExportWrapperXmlDto = new BranchExportWrapperXmlDto();
        branchExportWrapperXmlDto.setBranches(this.branchService.topBranches());
        return this.xmlParser.write(branchExportWrapperXmlDto);
    }
}

