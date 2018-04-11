package app.services;

import app.entitites.Part;
import app.entitites.Supplier;
import app.repositories.PartRepo;
import app.repositories.SupplierRepo;
import app.serializers.JsonSerializer;
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
    private final JsonSerializer jsonSerializer;

    @Autowired
    public PartService(PartRepo partRepo, SupplierRepo supplierRepo, JsonSerializer jsonSerializer) {
        this.partRepo = partRepo;
        this.supplierRepo = supplierRepo;
        this.jsonSerializer = jsonSerializer;
    }

    public void importParts(String fileName) {
        List<Supplier> suppliers = this.supplierRepo.findAll();
        Part[] parts = this.jsonSerializer.deserialize(Part[].class, fileName);
        Random random = new Random();
        for (Part part : parts) {
            int supplierId = random.nextInt(suppliers.size());
            part.setSupplier(suppliers.get(supplierId));
            suppliers.get(supplierId).getParts().add(part);
            this.partRepo.save(part);
        }
    }
}
