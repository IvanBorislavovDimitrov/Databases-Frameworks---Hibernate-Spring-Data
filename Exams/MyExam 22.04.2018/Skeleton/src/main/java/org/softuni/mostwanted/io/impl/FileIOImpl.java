package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.terminal.Terminal;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {

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
        try (
                OutputStream outputStream = new FileOutputStream(file);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        ) {
            bufferedWriter.write(fileContent);
        }
    }
}
