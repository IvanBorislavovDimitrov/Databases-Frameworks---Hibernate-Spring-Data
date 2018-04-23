package com.masdefect.terminal;

import com.masdefect.config.Config;
import com.masdefect.controller.*;
import com.masdefect.io.interfaces.ConsoleIO;
import com.masdefect.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final SolarSystemController solarSystemController;
    private final ConsoleIO consoleIO;
    private final StarsController starsController;
    private final PlanetsController planetsController;
    private final PersonsController personsController;
    private final AnomalyController anomalyController;
    private final AnomalyVictimsController anomalyVictimsController;

    @Autowired
    public Terminal(FileIO fileIO, SolarSystemController solarSystemController, ConsoleIO consoleIO, StarsController starsController, PlanetsController planetsController, PersonsController personsController, AnomalyController anomalyController, AnomalyVictimsController anomalyVictimsController) {
        this.fileIO = fileIO;
        this.solarSystemController = solarSystemController;
        this.consoleIO = consoleIO;
        this.starsController = starsController;
        this.planetsController = planetsController;
        this.personsController = personsController;
        this.anomalyController = anomalyController;
        this.anomalyVictimsController = anomalyVictimsController;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.importSolarSystems();
//        this.importStars();
//        this.importPlanets();
//        this.importPersons();
//        this.importAnomalies();
//        this.importAnomalyVictims();
//        this.importNewAnomalies();

//        this.allPlanetsWithNotTeleportation();
//        anomalyWhichAffectedMostPeople();
        orderdeAnomaies();
    }

    private void orderdeAnomaies() {
        System.out.println(this.anomalyController.exportAnomaliesOrdered());
    }

    private void anomalyWhichAffectedMostPeople() {
        System.out.println(this.anomalyController.findAnomalyWithMostVictims());
    }

    private void allPlanetsWithNotTeleportation() {
        System.out.println(this.planetsController.planetsWithNoPeopleTeleportedToThem());
    }

    private void importNewAnomalies() throws IOException {
        String xmlContent = this.fileIO.read(Config.ANOMALIES_IMPORT_XML);
        String log = this.anomalyController.importDataFromXML(xmlContent);
        this.consoleIO.write(log);
    }

    private void importAnomalyVictims() throws IOException {
        String jsonContent = this.fileIO.read(Config.ANOMALY_VICTIMS_IMPORT_JSON);
        String log = this.anomalyVictimsController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importAnomalies() throws IOException {
        String jsonContent = this.fileIO.read(Config.ANOMALY_IMPORT_JSON);
        String log = this.anomalyController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importPersons() throws IOException {
        String jsonContent = this.fileIO.read(Config.PERSONS_IMPORT_JSON);
        String log = this.personsController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importPlanets() throws IOException {
        String jsonContent = this.fileIO.read(Config.PLANETS_IMPORT_JSON);
        String log = this.planetsController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importStars() throws IOException {
        String jsonContent = this.fileIO.read(Config.STARS_IMPORT_JSON);
        String log = this.starsController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importSolarSystems() throws IOException {
        String jsonContent = this.fileIO.read(Config.SOLAR_SYSTEM_IMPORT_JSON);
        String log = this.solarSystemController.importDataFromJSON(jsonContent);
        this.consoleIO.write(log);
    }
}
