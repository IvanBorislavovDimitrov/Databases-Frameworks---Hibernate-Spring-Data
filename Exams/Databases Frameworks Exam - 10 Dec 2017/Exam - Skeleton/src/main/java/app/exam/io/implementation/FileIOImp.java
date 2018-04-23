package app.exam.io.implementation;

import app.exam.config.Config;
import app.exam.io.interfaces.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImp implements FileIO {

    @Override
    public String read(String file) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        InputStream inputStream = Config.class.getResourceAsStream(file);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }

        return sb.toString();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
		
    }
}
