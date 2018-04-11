package app.terminal;

import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String INPUT_SUPPLIERS_JSON = "/input/suppliers.json";
    private static final String INPUT_PARTS_JSON = "/input/parts.json";
    private static final String INPUT_CARS_JSON = "/input/cars.json";
    private static final String INPUT_CUSTOMERS_JSON = "/input/customers.json";
    private static final String OUTPUT_ORDERED_CUSTOMERS = "/ordered_customer.json";
    private static final String OUTPUT_CARS_FROM_MAKE_TOYOTA = "/toyota_cars.json";
    private static final String OUTPUT_LOCAL_SUPPLIERS = "/local_suppliers.json";
    private static final String OUTPUT_CARS_WITH_THEIR_LIST_OF_PARTS = "/cars_and_parts.json";
    private static final String OUTPUT_TOTAL_SALES_BY_CUSTOMER = "/total_sales_by_customer.json";
    private static final String OUTPUT_SALES_WITH_APPLIED_DISCOUNT = "/sales_with_applied_discount.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Terminal(SupplierService supplierService, PartService partService, CarService carService,
                    CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) {
//        this.seedTheDatabase();
//        this.orderedCustomers();
//        this.carsFromMakeToyota();
//        this.localSuppliers();
//        this.carsWithTheirListOfParts();
//        this.totalSalesByCustomer();
        this.salesWithAppliedDiscount();
    }

    private void salesWithAppliedDiscount() {
        this.saleService.salesWithAppliedDiscount(OUTPUT_SALES_WITH_APPLIED_DISCOUNT);
    }

    private void carsWithTheirListOfParts() {
        this.carService.carsWithTheirListOfParts(OUTPUT_CARS_WITH_THEIR_LIST_OF_PARTS);
    }

    private void localSuppliers() {
        this.supplierService.localSuppliers(OUTPUT_LOCAL_SUPPLIERS);
    }

    private void totalSalesByCustomer() {
        this.customerService.totalSaleByCustomer(OUTPUT_TOTAL_SALES_BY_CUSTOMER);
    }

    private void carsFromMakeToyota() {
        this.carService.carsFromMakeToyota(OUTPUT_CARS_FROM_MAKE_TOYOTA);
    }

    private void orderedCustomers() {
        this.customerService.orderedCustomers(OUTPUT_ORDERED_CUSTOMERS);
    }

    private void seedTheDatabase() {
        this.importSuppliers();
        this.importParts();
        this.importCars();
        this.importCustomers();
        this.importSales();
    }

    private void importSales() {
        this.saleService.importSales();
    }

    private void importCustomers() {
        this.customerService.importCustomers(INPUT_CUSTOMERS_JSON);
    }

    private void importCars() {
        this.carService.importCars(INPUT_CARS_JSON);
    }

    private void importParts() {
        this.partService.importParts(INPUT_PARTS_JSON);
    }

    private void importSuppliers() {
        this.supplierService.importSuppliersFromFile(INPUT_SUPPLIERS_JSON);
    }
}
