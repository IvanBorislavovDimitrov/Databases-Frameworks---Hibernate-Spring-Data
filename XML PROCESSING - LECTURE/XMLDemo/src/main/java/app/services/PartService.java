package app.services;

import app.repositories.PartRepo;
import app.repositories.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartService {

    private final PartRepo partRepo;
    private final SupplierRepo supplierRepo;

    @Autowired
    public PartService(PartRepo partRepo, SupplierRepo supplierRepo) {
        this.partRepo = partRepo;
        this.supplierRepo = supplierRepo;
    }
}
