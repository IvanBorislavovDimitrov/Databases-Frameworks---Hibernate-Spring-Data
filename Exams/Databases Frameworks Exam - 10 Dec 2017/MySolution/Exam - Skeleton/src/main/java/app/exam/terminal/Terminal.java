package app.exam.terminal;

import app.exam.config.Config;
import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final EmployeesController employeesController;
    private final ItemsController itemsController;
    private final OrdersController ordersController;
    private final CategoryController categoryController;

    @Autowired
    public Terminal(FileIO fileIO, EmployeesController employeesController, ItemsController itemsController, OrdersController ordersController, CategoryController categoryController) {
        this.fileIO = fileIO;
        this.employeesController = employeesController;
        this.itemsController = itemsController;
        this.ordersController = ordersController;
        this.categoryController = categoryController;
    }

    @Override
    public void run(String... args) throws Exception {
        this.importEmployees();
        this.importItems();
        this.importOrders();
        System.out.println(this.ordersController.exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo"));

        List<String> names = new ArrayList<>();
        names.add("Chicken");
        names.add("Toys");
        names.add("Drinks");
        System.out.println(categoryController.getCategoriesWithMostPopularItemsSorted(names));
    }

    private void importOrders() throws IOException {
        String xmlContent = this.fileIO.read(Config.ORDERS_IMPORT_XML);
        System.out.println(this.ordersController.importDataFromXML(xmlContent));
    }

    private void importItems() throws IOException {
        String jsonContent = this.fileIO.read(Config.ITEMS_IMPORT_JSON);
        System.out.println(this.itemsController.importDataFromJSON(jsonContent));
    }

    private void importEmployees() throws IOException {
        String jsonContent = this.fileIO.read(Config.EMPLOYEES_IMPORT_JSON);
        System.out.println(this.employeesController.importDataFromJSON(jsonContent));
    }
}
