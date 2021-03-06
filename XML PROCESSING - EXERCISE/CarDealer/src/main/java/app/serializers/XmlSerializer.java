package app.serializers;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class XmlSerializer {

    public <T> T deserialize(Class<T> classObj, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classObj);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T obj = (T) unmarshaller.unmarshal(new File(fileName));

        return obj;
    }

    public <T> void serialize(T obj, String fileName) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(obj, new FileWriter(fileName));
    }
}
