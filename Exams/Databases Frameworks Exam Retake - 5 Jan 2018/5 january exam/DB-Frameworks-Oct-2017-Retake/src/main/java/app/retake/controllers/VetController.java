package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.XMLParser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class VetController {

    private final VetService vetService;
    private final XMLParser xmlParser;

    @Autowired
    public VetController(VetService vetService, XMLParser xmlParser) {
        this.vetService = vetService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            VetWrapperXMLImportDTO vetWrapper = this.xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);
            for (VetXMLImportDTO vetDto : vetWrapper.getVets()) {
                try {
                    this.vetService.create(vetDto);
                    sb.append(String.format("Record %s successfully imported.\r\n", vetDto.getName()));
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data:\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
