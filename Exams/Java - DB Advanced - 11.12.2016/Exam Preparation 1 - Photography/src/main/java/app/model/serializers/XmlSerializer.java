package app.model.serializers;

import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import java.io.File;

@Component
public class XmlSerializer {

    public <T> T deserialize(Class<T> classObj, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classObj);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        T obj = (T) unmarshaller.unmarshal(new File(fileName));

        return obj;
    }

    public <T> void serialize(T obj, String fileName) throws JAXBException {
        String pathName = "src/main/resources/";
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, new File(pathName + fileName));
    }
}
