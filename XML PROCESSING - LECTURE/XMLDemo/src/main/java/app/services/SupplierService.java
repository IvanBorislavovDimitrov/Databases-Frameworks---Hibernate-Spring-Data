package app.services;

import app.entities.Part;
import app.entities.Supplier;
import app.entities.dtos.dtos_suppliers.SupplierDto;
import app.entities.dtos.dtos_suppliers.SuppliersDto;
import app.entities.dtos.visible_dtos.PartDto;
import app.entities.dtos.visible_dtos.Parts;
import app.repositories.SupplierRepo;
import app.serializers.XmlSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SupplierService {

    private final SupplierRepo supplierRepo;
    private final XmlSerializer xmlSerializer;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo, XmlSerializer xmlSerializer) {
        this.supplierRepo = supplierRepo;
        this.xmlSerializer = xmlSerializer;
    }

    public void exportSuppliers(String fileName) {
        List<Supplier> suppliers = this.supplierRepo.findAll();
        SuppliersDto suppliersDto = new SuppliersDto();
        suppliersDto.setSuppliersDtos(new ArrayList<>());
        for (Supplier supplier : suppliers) {
            SupplierDto supplierDto = new SupplierDto();
            supplierDto.setName(supplier.getName());
            supplierDto.setImporter(supplier.isImporter());
            supplierDto.setParts(new Parts());
            for (Part part : supplier.getParts()) {
                PartDto partDto = new PartDto();
                partDto.setName(part.getName());
                partDto.setPrice(part.getPrice());
                partDto.setQuantity(part.getQuantity());
                supplierDto.getParts().getPartDtos().add(partDto);
            }
            suppliersDto.getSuppliersDtos().add(supplierDto);
        }

        try {
            this.xmlSerializer.exportToFile(suppliersDto, fileName);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
