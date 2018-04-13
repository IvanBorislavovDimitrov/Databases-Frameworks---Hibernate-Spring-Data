package app.terminal;

import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String OUTPUT_CARS_INFO_XML = "src/main/resources/output/cars.xml";
    private static final String OUTPUT_SUPPLIERS_INFO_XML = "src/main/resources/output/suppliers.xml";

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
        this.supplierService.exportSuppliers(OUTPUT_SUPPLIERS_INFO_XML);
    }
}
