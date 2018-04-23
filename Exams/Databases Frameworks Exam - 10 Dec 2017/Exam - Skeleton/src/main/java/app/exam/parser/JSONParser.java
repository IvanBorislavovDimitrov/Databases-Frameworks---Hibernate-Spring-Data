package app.exam.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.Parser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        T obj = this.gson.fromJson(fileContent, objectClass);

        return obj;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        String json = this.gson.toJson(object);

        return json;
    }
}
