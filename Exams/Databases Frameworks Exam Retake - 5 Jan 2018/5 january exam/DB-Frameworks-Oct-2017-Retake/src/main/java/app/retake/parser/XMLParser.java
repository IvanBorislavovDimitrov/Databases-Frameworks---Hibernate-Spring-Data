package app.retake.parser;

import app.retake.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller =  jaxbContext.createUnmarshaller();
        T obj = (T) unmarshaller.unmarshal(new StreamSource(new StringReader(fileContent)));


        return obj;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw);

        return sw.toString();
    }
}
