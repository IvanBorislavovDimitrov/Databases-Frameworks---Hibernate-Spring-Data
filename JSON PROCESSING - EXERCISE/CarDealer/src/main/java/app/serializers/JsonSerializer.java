package app.serializers;

import app.io.FileIO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonSerializer {

    private Gson gson;
    private FileIO fileIO;

    public JsonSerializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.fileIO = new FileIO();
    }

    public <T> T deserialize(Class<T> classObj, String fileName) {
        String content = this.fileIO.readFile(fileName);
        T obj = this.gson.fromJson(content, classObj);

        return obj;
    }

    public <T> void serialize(T obj, String fileName) throws IOException {
        String json = this.gson.toJson(obj);

        this.fileIO.writeFile(json, fileName);
    }
}
