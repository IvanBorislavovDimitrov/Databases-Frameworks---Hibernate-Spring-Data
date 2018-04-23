package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.parser.JSONParser;
import app.retake.parser.XMLParser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final XMLParser xmlParser;
    private final JSONParser jsonParser;

    @Autowired
    public ProcedureController(ProcedureService procedureService, XMLParser xmlParser, JSONParser jsonParser) {
        this.procedureService = procedureService;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            ProcedureWrapperXMLImportDTO procedureWrapper = this.xmlParser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
            for (ProcedureXMLImportDTO procedureDto : procedureWrapper.getProcedures()) {
                try {
                    this.procedureService.create(procedureDto);
                    sb.append("Record successfully imported.\r\n");
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data.\r\n");
                }
            }
        } catch (IOException | JAXBException | ParseException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        return this.xmlParser.write(this.procedureService.exportProcedures());
    }
}
