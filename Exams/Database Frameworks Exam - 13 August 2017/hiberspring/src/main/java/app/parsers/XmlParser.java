package app.parsers;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XmlParser {

    public <T> T read(Class<T> objCls, String xmlContent) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(objCls);
            Unmarshaller unmarshaller =  jaxbContext.createUnmarshaller();
            T obj = (T) unmarshaller.unmarshal(new StreamSource(new StringReader(xmlContent)));

            return obj;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> String write(T obj) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(obj, stringWriter);

            return stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
