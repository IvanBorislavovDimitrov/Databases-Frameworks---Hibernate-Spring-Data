package app.serializer;

import app.io.FileIO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@Transactional
public class JsonSerializer {

    private Gson gson;

    public JsonSerializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> T deserialize(Class<T> classObj, String fileName) {
        String json = new FileIO().readFile(fileName);
        return gson.fromJson(json, classObj);
    }

    public <T> void serialize(T t, String fileName) throws IOException {
        String json = this.gson.toJson(t);
        new FileIO().writeFile(json, fileName);
    }
}
