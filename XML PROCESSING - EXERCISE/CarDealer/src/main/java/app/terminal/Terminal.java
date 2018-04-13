package app.terminal;

import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String INPUT_SUPPLIERS_JSON = "src/main/resources/input/suppliers.xml";
    private static final String INPUT_PARTS_JSON = "src/main/resources/input/parts.xml";
    private static final String INPUT_CARS_JSON = "src/main/resources/input/cars.xml";
    private static final String INPUT_CUSTOMERS_JSON = "src/main/resources/input/customers.xml";
    private static final String OUTPUT_ORDERED_CUSTOMERS = "src/main/resources/output/ordered_customer.xml";
    private static final String OUTPUT_CARS_FROM_MAKE_TOYOTA = "src/main/resources/output/toyota_cars.xml";
    private static final String OUTPUT_LOCAL_SUPPLIERS = "src/main/resources/output/local_suppliers.xml";
    private static final String OUTPUT_CARS_WITH_THEIR_LIST_OF_PARTS = "src/main/resources/output/cars_and_parts.xml";
    private static final String OUTPUT_TOTAL_SALES_BY_CUSTOMER = "src/main/resources/output/total_sales_by_customer.xml";
    private static final String OUTPUT_SALES_WITH_APPLIED_DISCOUNT = "src/main/resources/output/sales_with_applied_discount.xml";

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
    public void run(String... args) throws JAXBException {
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

    private void seedTheDatabase() throws JAXBException {
        this.importSuppliers();
        this.importParts();
        this.importCars();
        this.importCustomers();
        this.importSales();
    }

    private void importSales() {
        this.saleService.importSales();
    }

    private void importCustomers() throws JAXBException {
        this.customerService.importCustomers(INPUT_CUSTOMERS_JSON);
    }

    private void importCars() throws JAXBException {
        this.carService.importCars(INPUT_CARS_JSON);
    }

    private void importParts() throws JAXBException {
        this.partService.importParts(INPUT_PARTS_JSON);
    }

    private void importSuppliers() throws JAXBException {
        this.supplierService.importSuppliersFromFile(INPUT_SUPPLIERS_JSON);
    }
}
