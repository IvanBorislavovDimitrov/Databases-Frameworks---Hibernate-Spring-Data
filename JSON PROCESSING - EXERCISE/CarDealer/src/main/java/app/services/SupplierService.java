package app.services;

import app.entitites.Supplier;
import app.entitites.dtos.SupplierShortInfoDto;
import app.repositories.SupplierRepo;
import app.serializers.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierService {

    private final SupplierRepo supplierRepo;
    private final JsonSerializer jsonSerializer;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo, JsonSerializer jsonSerializer) {
        this.supplierRepo = supplierRepo;
        this.jsonSerializer = jsonSerializer;
    }

    public void localSuppliers(String fileName) {
        List<Supplier> nonImporterSuppliers = this.supplierRepo.findAll()
                .stream().filter(x -> ! x.isImporter()).collect(Collectors.toList());

        List<SupplierShortInfoDto> supplierShortInfoDtos = new ArrayList<>();
        for (Supplier supplier : nonImporterSuppliers) {
            SupplierShortInfoDto supplierShortInfoDto = new SupplierShortInfoDto();
            supplierShortInfoDto.setId(supplier.getId());
            supplierShortInfoDto.setName(supplier.getName());
            supplierShortInfoDto.setPartsCount(supplier.getParts().size());
            supplierShortInfoDtos.add(supplierShortInfoDto);
        }
        supplierShortInfoDtos.sort((x1, x2) -> Integer.compare(x2.getPartsCount(), x1.getPartsCount()));
        try {
            this.jsonSerializer.serialize(supplierShortInfoDtos, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importSuppliersFromFile(String fileName) {
        Supplier[] suppliers = this.jsonSerializer.deserialize(Supplier[].class, fileName);
        for (Supplier supplier : suppliers) {
            this.supplierRepo.save(supplier);
        }
    }
}
