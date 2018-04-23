package app.exam.io.imp;

import app.exam.io.interfaces.FileIO;
import app.exam.terminal.Terminal;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class FileIOimp implements FileIO {
    @Override
    public String read(String file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (
                InputStream inputStream = Terminal.class.getResourceAsStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                ) {
            String line;
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
