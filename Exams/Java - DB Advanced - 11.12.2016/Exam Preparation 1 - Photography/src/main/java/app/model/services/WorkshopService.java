package app.model.services;

import javax.xml.bind.JAXBException;
import java.text.ParseException;

public interface WorkshopService {

    void importWorkshopsInTheDatabase(String fileName) throws JAXBException, ParseException;
    void workshopsByLocations(String fileName) throws JAXBException;
}
