package app.model.services;

import app.model.entities.Photographer;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface PhotographerService {
    void importPhotographersToTheDatabase(String fileName);
    void orderedCustomers(String fileName);
    void landScapePhotographers(String fileName);
    void sameCamerasPhotographers(String fileName) throws JAXBException;
}
