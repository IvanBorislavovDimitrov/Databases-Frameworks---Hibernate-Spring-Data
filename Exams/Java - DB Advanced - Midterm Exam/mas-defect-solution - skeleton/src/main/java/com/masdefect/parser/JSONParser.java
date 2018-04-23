package com.masdefect.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.masdefect.parser.interfaces.FileParser;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements FileParser {

    private final Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException {
        T obj = this.gson.fromJson(fileContent, objectClass);

        return obj;
    }

    @Override
    public <T> String write(T object, String fileContent) throws IOException {
        String jsonContent = this.gson.toJson(object);
        return jsonContent;
    }
}
