package app.services;

import app.entitites.Car;
import app.entitites.Customer;
import app.entitites.Part;
import app.entitites.Sale;
import app.entitites.dtos.CarInfoDto;
import app.entitites.dtos.CarInfoDto1;
import app.entitites.dtos.SaleWithDiscountDto;
import app.repositories.CarRepo;
import app.repositories.CustomerRepo;
import app.repositories.SaleRepo;
import app.serializers.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class SaleService {

    private final SaleRepo saleRepo;
    private final JsonSerializer jsonSerializer;
    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    private final double[] discounts = {0, 5, 10, 15, 20, 30, 40, 50};

    @Autowired
    public SaleService(SaleRepo saleRepo, JsonSerializer jsonSerializer, CarRepo carRepo, CustomerRepo customerRepo) {
        this.saleRepo = saleRepo;
        this.jsonSerializer = jsonSerializer;
        this.carRepo = carRepo;
        this.customerRepo = customerRepo;
    }

    public void importSales() {
        List<Customer> customers = this.customerRepo.findAll();
        List<Car> cars = this.carRepo.findAll();
        Random random = new Random();

        while (cars.size() > 0) {
            int customerId = random.nextInt(customers.size());
            int carId = random.nextInt(cars.size());
            double discount = this.discounts[random.nextInt(this.discounts.length)];
            Sale sale = new Sale(discount, cars.get(carId), customers.get(customerId));
            cars.remove(cars.get(carId));
            this.saleRepo.save(sale);
        }
    }

    public void salesWithAppliedDiscount(String fileName) {
        List<Sale> sales = this.saleRepo.findAll();
        List<SaleWithDiscountDto> salesWithDiscounts = new ArrayList<>();
        for (Sale sale : sales) {
            SaleWithDiscountDto saleWithDiscountDto = new SaleWithDiscountDto();
            CarInfoDto1 carInfoDto1 = new CarInfoDto1();
            carInfoDto1.setMake(sale.getCar().getMake());
            carInfoDto1.setModel(sale.getCar().getModel());
            carInfoDto1.setTravelledDistance(sale.getCar().getTravelledDistance());
            saleWithDiscountDto.setCar(carInfoDto1);
            saleWithDiscountDto.setCustomerName(sale.getCustomer().getName());
            saleWithDiscountDto.setDiscount(sale.getDiscount());
            saleWithDiscountDto.setPrice(this.getPriceWithoutDiscount(sale));
            saleWithDiscountDto.setPriceWithDiscount(this.getPriceWithDiscount(sale));

            salesWithDiscounts.add(saleWithDiscountDto);
        }

        try {
            this.jsonSerializer.serialize(salesWithDiscounts, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getPriceWithDiscount(Sale sale) {
        double sum = 0;
        for (Part part : sale.getCar().getParts()) {
            sum += part.getPrice();
        }

        sum *= (100 - sale.getDiscount()) / 100;
        if (sale.getCustomer().isYoungDriver()) {
            sum *= 0.95;
        }

        return sum;
    }

    private double getPriceWithoutDiscount(Sale sale) {
        double sum = 0;
        for (Part part : sale.getCar().getParts()) {
            sum += part.getPrice();
        }

        return sum;
    }

}
