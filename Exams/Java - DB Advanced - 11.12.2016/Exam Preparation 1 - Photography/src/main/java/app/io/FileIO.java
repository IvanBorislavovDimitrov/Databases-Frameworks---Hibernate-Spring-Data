package app.io;

import app.terminal.Terminal;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIO {

    public String readFile(String fileName) {
        try (
                InputStream stream = Terminal.class.getResourceAsStream(fileName);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                ) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeFile(String content, String fileName) {
        try (
                OutputStream outputStream = new FileOutputStream(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                ) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
