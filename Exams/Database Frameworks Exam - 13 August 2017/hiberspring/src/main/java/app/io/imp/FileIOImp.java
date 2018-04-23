package app.io.imp;

import app.io.api.FileIO;
import app.terminal.Terminal;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImp implements FileIO {

    @Override
    public String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (
                InputStream inputStream = Terminal.class.getResourceAsStream(fileName);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    public void writeFile(String fileContent, String fileName) {
        try (
                OutputStream outputStream = new FileOutputStream(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                ) {
            bufferedWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
