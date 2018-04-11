package app.io;

import app.terminal.Terminal;

import java.io.*;

public class FileIO {

    public String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        String line;
        InputStream stream = Terminal.class.getResourceAsStream(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public void writeFile(String content, String fileName) throws FileNotFoundException {
        String namePath = System.getProperty("user.dir") + "/src/main/resources/ouput/";
        PrintWriter printWriter = new PrintWriter(namePath + fileName);
        printWriter.write(content);
        printWriter.close();
    }
}
