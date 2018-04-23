package app.model.services;

import javax.xml.bind.JAXBException;

public interface AccessoryService {

    void importAccessoriesToTheDatabase(String fileName) throws JAXBException;
}
