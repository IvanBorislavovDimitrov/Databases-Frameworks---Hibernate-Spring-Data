package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Terminal implements CommandLineRunner {

    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final ProcedureController procedureController;

    @Autowired
    public Terminal(AnimalAidController animalAidController, AnimalController animalController, VetController vetController, FileIO fileIO, ConsoleIO consoleIO, ProcedureController procedureController) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.seed();
        System.out.println(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"));
        this.fileIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"), Config.EXPORT_ANIMALS_JSON);
        System.out.println(this.procedureController.exportProcedures());
        this.fileIO.write(this.procedureController.exportProcedures(), Config.EXPORT_PROCEDURES_XML);
    }

    private void seed() throws IOException {
        this.importAnimalAids();
        this.importAnimals();
        this.importVets();
        this.importProcedures();
    }

    private void importProcedures() throws IOException {
        String xmlContent = this.fileIO.read(Config.PROCEDURES_IMPORT_XML);
        String log = this.procedureController.importDataFromXML(xmlContent);
        this.consoleIO.write(log);
    }

    private void importVets() throws IOException {
        String xmlContent = this.fileIO.read(Config.VETS_IMPORT_XML);
        String log = this.vetController.importDataFromXML(xmlContent);
        this.consoleIO.write(log);
    }

    private void importAnimals() throws IOException {
        String jsonContent = this.fileIO.read(Config.ANIMALS_IMPORT_JSON);
        String log = this.animalController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importAnimalAids() throws IOException {
        String jsonContent = this.fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON);
        String log = this.animalAidController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }
}
