package app.io;

import app.terminal.Terminal;

import java.io.*;

public class FileIO {

    public String readFile(String fileName) {
        StringBuilder text = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(Terminal.class.getResourceAsStream(fileName)))) {
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    public void writeFile(String content, String fileName) throws FileNotFoundException {
        String namePath = System.getProperty("user.dir") + "/src/main/resources/output/";
        PrintWriter printWriter = new PrintWriter(namePath + fileName);
        printWriter.write(content);
        printWriter.close();
    }
}
