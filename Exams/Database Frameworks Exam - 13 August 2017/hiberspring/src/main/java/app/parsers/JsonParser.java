package app.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {

    private Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder().excludeFieldsWithModifiers().setPrettyPrinting().create();
    }

    public <T> String write(T obj) {
        return this.gson.toJson(obj);
    }

    public <T> T read(Class<T> objCls, String jsonContent) {
        return this.gson.fromJson(jsonContent, objCls);
    }
}
