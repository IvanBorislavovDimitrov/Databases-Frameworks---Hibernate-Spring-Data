package com.masdefect.parser;

import com.masdefect.parser.interfaces.FileParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component(value = "XMLParser")
public class XMLParser implements FileParser {

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        T obj = (T) unmarshaller.unmarshal(new StreamSource(new StringReader(fileContent)));

        return obj;
    }

    @Override
    public <T> String write(T object, String fileContent) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw);

        return sw.toString();
    }
}
