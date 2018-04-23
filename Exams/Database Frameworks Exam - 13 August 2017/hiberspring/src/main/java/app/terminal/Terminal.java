package app.terminal;

import app.config.Config;
import app.controllers.*;
import app.io.api.ConsoleIO;
import app.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final TownController townController;
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final BranchController branchController;
    private final EmployeeCardController employeeCardController;
    private final ProductController productController;
    private final EmployeeController employeeController;

    @Autowired
    public Terminal(TownController townController, FileIO fileIO, ConsoleIO consoleIO, BranchController branchController, EmployeeCardController employeeCardController, ProductController productController, EmployeeController employeeController) {
        this.townController = townController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.branchController = branchController;
        this.employeeCardController = employeeCardController;
        this.productController = productController;
        this.employeeController = employeeController;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.importTowns();
        this.importBranches();
        this.importEmployeeCards();
        this.importProducts();
        this.importEmployees();
        this.getFreeCards();
        this.getProductiveEmployees();
        this.getTownsInfo();
        this.getTopBranches();
    }

    private void getTopBranches() {
        String line;
        System.out.println(line = this.branchController.topBranches());
        this.fileIO.writeFile(line, Config.OUTPUT_TOP_BRANCHES_JSON);
    }

    private void getTownsInfo() {
        String line;
        System.out.println(line = this.townController.getTownsInfo());
        this.fileIO.writeFile(line, Config.OUTPUT_TOWNS_JSON);
    }

    private void getProductiveEmployees() {
        String line;
        System.out.println(line = this.employeeController.getProductiveEmployeesInJson());
        this.fileIO.writeFile(line, Config.OUTPUT_PRODUCTIVE_EMPLOYEES_JSON);
    }

    private void getFreeCards() {
        String line;
        System.out.println(line = this.employeeCardController.freeCards());
        this.fileIO.writeFile(line, Config.OUTPUT_FREE_CARDS_JSON);
    }

    private void importEmployees() {
        String xmlContent = this.fileIO.readFile(Config.INPUT_EMPLOYEES_XML);
        String log = this.employeeController.importEmployeesFromXML(xmlContent);
        this.consoleIO.write(log);
    }

    private void importProducts() {
        String xmlContent = this.fileIO.readFile(Config.INPUT_PRODUCTS_XML);
        String log = this.productController.importProductsFromXML(xmlContent);
        this.consoleIO.write(log);
    }


    private void importEmployeeCards() {
        String jsonContent = this.fileIO.readFile(Config.INPUT_EMPLOYEE_CARDS_JSON);
        String log = this.employeeCardController.importEmployeeCardsFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importBranches() {
        String jsonContent = this.fileIO.readFile(Config.INPUT_BRANCHES_JSON);
        String log = this.branchController.importBranchesFromJSON(jsonContent);
        this.consoleIO.write(log);
    }

    private void importTowns() {
        String jsonContent = this.fileIO.readFile(Config.INPUT_TOWNS_JSON);
        String log = this.townController.importTownsFromJSON(jsonContent);
        this.consoleIO.write(log);
    }
}
