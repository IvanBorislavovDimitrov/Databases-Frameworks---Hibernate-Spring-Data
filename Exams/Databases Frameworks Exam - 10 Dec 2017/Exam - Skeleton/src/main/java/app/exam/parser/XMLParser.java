package app.exam.parser;

import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T obj = (T) unmarshaller.unmarshal(new StreamSource(new StringReader(fileContent)));

        return obj;
    }

    @Override
    public <T> String write(T object) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);

        return stringWriter.toString();
    }
}
