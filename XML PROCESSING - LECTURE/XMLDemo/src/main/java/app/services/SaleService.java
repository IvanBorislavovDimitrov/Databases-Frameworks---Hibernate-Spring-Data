package app.services;

import app.repositories.CarRepo;
import app.repositories.CustomerRepo;
import app.repositories.SaleRepo;
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
    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;

    @Autowired
    public SaleService(SaleRepo saleRepo, CarRepo carRepo, CustomerRepo customerRepo) {
        this.saleRepo = saleRepo;
        this.carRepo = carRepo;
        this.customerRepo = customerRepo;
    }



}
