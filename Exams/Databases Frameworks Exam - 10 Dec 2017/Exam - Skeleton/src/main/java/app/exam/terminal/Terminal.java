package app.exam.terminal;

import app.exam.config.Config;
import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.ConsoleIO;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;


@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final EmployeesController employeesController;
    private final ItemsController itemsController;
    private final ConsoleIO consoleIO;
    private final OrdersController ordersController;
    private final CategoryController categoryController;

    @Autowired
    public Terminal(FileIO fileIO, EmployeesController employeesController, ItemsController itemsController, ConsoleIO consoleIO, OrdersController ordersController, CategoryController categoryController) {
        this.fileIO = fileIO;
        this.employeesController = employeesController;
        this.itemsController = itemsController;
        this.consoleIO = consoleIO;
        this.ordersController = ordersController;
        this.categoryController = categoryController;
    }

    @Override
    public void run(String... args) throws Exception {
        this.importEmployees();
        this.importItems();
        importOrders();
        this.consoleIO.write(this.ordersController
                .exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo"));
    }

    private void importOrders() throws IOException, JAXBException {
        String fileContent = this.fileIO.read(Config.ORDERS_IMPORT_XML);
        String log = this.ordersController.importDataFromXML(fileContent);
        this.consoleIO.write(log);
    }

    private void importItems() throws IOException, JAXBException {
        String fileContent = this.fileIO.read(Config.ITEMS_IMPORT_JSON);
        String log = this.itemsController.importDataFromJSON(fileContent);
        this.consoleIO.write(log);
    }

    private void importEmployees() throws IOException {
        String fileContent = this.fileIO.read(Config.EMPLOYEES_IMPORT_JSON);
        String log = this.employeesController.importDataFromJSON(fileContent);
        this.consoleIO.write(log);
    }
}
