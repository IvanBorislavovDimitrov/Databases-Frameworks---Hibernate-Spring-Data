package app.io.imp;

import app.io.api.ConsoleIO;
import org.springframework.stereotype.Component;

@Component
public class ConsoleIOImp implements ConsoleIO {

    @Override
    public void write(String output) {
        System.out.println(output);
    }
}
