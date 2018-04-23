package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.configurations.Config;
import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Terminal implements CommandLineRunner {

    private final TownController townController;
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final CarController carController;
    private final RaceEntryController raceEntryController;
    private final RaceController raceController;

    @Autowired
    public Terminal(TownController townController, FileIO fileIO, ConsoleIO consoleIO, DistrictController districtController, RacerController racerController, CarController carController, RaceEntryController raceEntryController, RaceController raceController) {
        this.townController = townController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceEntryController = raceEntryController;
        this.raceController = raceController;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedTheDatabase();
        this.racingTowns();
        this.racingCars();
        this.mostWanted();
    }

    private void mostWanted() throws IOException {
        String jsonContent = this.racerController.mostWanted();
        this.consoleIO.write(jsonContent);
        this.fileIO.write(jsonContent, Config.MOST_WANTED_EXPORT_JSON);
    }

    private void racingCars() throws IOException {
        String jsonContent = this.racerController.racingCars();
        this.consoleIO.write(jsonContent);
        this.fileIO.write(jsonContent, Config.RACING_CARS_EXPORT_JSON);
    }

    private void racingTowns() throws IOException {
        String jsonContent = this.townController.racingTownsJSONFormat();
        this.consoleIO.write(jsonContent);
        this.fileIO.write(jsonContent, Config.RACING_TOWNS_EXPORT_JSON);
    }

    private void seedTheDatabase() throws IOException {
        this.importTowns();
        this.importDistricts();
        this.importRacers();
        this.importCars();
        this.importRaceEntries();
        this.importRaces();
    }

    private void importRaces() throws IOException {
        String xmlContent = this.fileIO.read(Config.RACES_IMPORT_XML);
        String log = this.raceController.importFromJson(xmlContent);
        this.consoleIO.write(log);
    }

    private void importRaceEntries() throws IOException {
        String xmlContent = this.fileIO.read(Config.RACE_ENTRIES_IMPORT_XML);
        String log = this.raceEntryController.importFromJson(xmlContent);
        this.consoleIO.write(log);
    }

    private void importCars() throws IOException {
        String jsonContent = this.fileIO.read(Config.CARS_IMPORT_JSON);
        String log = this.carController.importFromJson(jsonContent);
        this.consoleIO.write(log);
    }

    private void importRacers() throws IOException {
        String jsonContent = this.fileIO.read(Config.RACERS_IMPORT_JSON);
        String log = this.racerController.importFromJson(jsonContent);
        this.consoleIO.write(log);
    }

    private void importDistricts() throws IOException {
        String jsonContent = this.fileIO.read(Config.DISTRICT_IMPORT_JSON);
        String log = this.districtController.importFromJson(jsonContent);
        this.consoleIO.write(log);
    }

    private void importTowns() throws IOException {
        String jsonContent = this.fileIO.read(Config.TOWNS_IMPORT_JSON);
        String log = this.townController.importFromJson(jsonContent);
        this.consoleIO.write(log);
    }
}
