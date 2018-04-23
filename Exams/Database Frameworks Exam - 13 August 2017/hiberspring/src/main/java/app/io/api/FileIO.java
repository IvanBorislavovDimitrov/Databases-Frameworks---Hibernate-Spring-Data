package app.io.api;

public interface FileIO {

    String readFile(String fileName);

    void writeFile(String fileContent, String fileName);
}
