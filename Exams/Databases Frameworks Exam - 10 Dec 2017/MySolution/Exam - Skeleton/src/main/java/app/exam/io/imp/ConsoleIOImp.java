package app.exam.io.imp;

import app.exam.io.interfaces.ConsoleIO;
import org.springframework.stereotype.Component;

@Component
public class ConsoleIOImp implements ConsoleIO {
    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
