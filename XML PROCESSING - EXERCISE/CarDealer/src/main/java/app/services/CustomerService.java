package app.services;

import app.entitites.Customer;
import app.entitites.Part;
import app.entitites.Sale;
import app.entitites.dtos.queries.total_sales_by_customer.CustomerInfo;
import app.entitites.dtos.queries.ordered_customers.CustomerWithSales;
import app.entitites.dtos.queries.ordered_customers.SaleDto;
import app.entitites.dtos.import_dtos.customers.CustomerDtoImport;
import app.entitites.dtos.import_dtos.customers.CustomerWrapperDtoImport;
import app.entitites.dtos.queries.ordered_customers.CustomerWithSalesWrapper;
import app.entitites.dtos.queries.total_sales_by_customer.CustomerInfoWrapper;
import app.repositories.CustomerRepo;
import app.serializers.XmlSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, XmlSerializer xmlSerializer) {
        this.customerRepo = customerRepo;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    public void importCustomers(String fileName) throws JAXBException {
        CustomerWrapperDtoImport customerWrapperDtoImport = this.xmlSerializer.deserialize(CustomerWrapperDtoImport.class, fileName);
        for (CustomerDtoImport customerDtoImport : customerWrapperDtoImport.getCustomerDtoImports()) {
            Customer customer = this.mapper.map(customerDtoImport, Customer.class);
            this.customerRepo.save(customer);
        }
    }

    public void orderedCustomers(String fileName) {
        List<Customer> customers = this.customerRepo.findAll();
        List<CustomerWithSales> customersWithSales = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerWithSales customerWithSales = this.mapper.map(customer, CustomerWithSales.class);
            for (Sale sale : customer.getSales()) {
                SaleDto saleDto = new SaleDto(sale.getDiscount(),
                        sale.getCar().getMake() + " " + sale.getCar().getModel());
               // customerWithSales.getSetOfSalesWrapperDto().getSaleDtos().add(saleDto);
            }
            customersWithSales.add(customerWithSales);
        }
        customersWithSales.sort((x1, x2) -> {
            if (x1.getBirthDate().compareTo(x2.getBirthDate()) == 0) {
                return Boolean.compare(x1.isYoungDriver(), x2.isYoungDriver());
            }

            return x1.getBirthDate().compareTo(x2.getBirthDate());
        });
        try {
            CustomerWithSalesWrapper customerWrapperDtoImport = new CustomerWithSalesWrapper();
            customerWrapperDtoImport.setCustomerWithSales(customersWithSales);
            this.xmlSerializer.serialize(customerWrapperDtoImport, fileName);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void totalSaleByCustomer(String fileName) {
        List<Customer> customersWithAtLeastOneBoughtCar = this.customerRepo.findAll()
                .stream()
                .filter(x -> x.getSales().size() > 1)
                .collect(Collectors.toList());
        List<CustomerInfo> customersInfo = new ArrayList<>();
        for (Customer customer : customersWithAtLeastOneBoughtCar) {
            CustomerInfo customerInfo = new CustomerInfo();
            customerInfo.setFullName(customer.getName());
            customerInfo.setBoughtCars(customer.getSales().size());
            customerInfo.setSpentMoney(getMoneySpentWithDiscountsAndYoungDriverDiscount(customer));
            customersInfo.add(customerInfo);
        }

        customersInfo.sort(((x2, x1) -> {
            if (x1.getBoughtCars() == x2.getBoughtCars()) {
                return Double.compare(x1.getSpentMoney(), x2.getSpentMoney());
            }

            return Integer.compare(x1.getBoughtCars(), x2.getBoughtCars());
        }));

        try {
            CustomerInfoWrapper ciw = new CustomerInfoWrapper();
            ciw.setCustomerInfos(customersInfo);
            this.xmlSerializer.serialize(ciw, fileName);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    private double getMoneySpentWithDiscountsAndYoungDriverDiscount(Customer customer) {
        double total = 0;
        for (Sale sale : customer.getSales()) {
            double sum = 0;
            for (Part part : sale.getCar().getParts())  {
                sum += part.getPrice();
            }
            sum *= (100 - sale.getDiscount()) / 100;
            if (customer.isYoungDriver()) {
                sum *= 0.95;
            }
            total += sum;
        }

        return total;
    }
}
