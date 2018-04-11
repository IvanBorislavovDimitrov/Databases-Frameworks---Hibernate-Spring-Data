package app.serialize;

import app.io.FileIO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;


@Component(value = "JsonSerializer")
public class JsonSerializer {

    private Gson gson; // for exam

    public JsonSerializer() {
        this.gson = new GsonBuilder()
                    .excludeFieldsWithModifiers()
                    .setPrettyPrinting()
                    .create();
    }

    public <T> T deserialize(Class<T> classObj, String fileName) {
        T obj =  this.gson.fromJson(FileIO.readFile(fileName), classObj);

        return obj;
    }

    public <T> String serialize(T obj) {
        return this.gson.toJson(obj);
    }
}
