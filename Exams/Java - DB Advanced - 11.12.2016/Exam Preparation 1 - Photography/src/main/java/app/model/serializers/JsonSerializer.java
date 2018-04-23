package app.model.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonSerializer {

    private Gson gson;

    public JsonSerializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> T deserialize(Class<T> classObj, String jsonString) {
        T obj = this.gson.fromJson(jsonString, classObj);

        return obj;
    }

    public <T> String serialize(T obj) {
        return this.gson.toJson(obj);
    }
}
