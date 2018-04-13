package app.services;

import app.entitites.Part;
import app.entitites.Supplier;
import app.entitites.dtos.import_dtos.parts.PartDtoImport;
import app.entitites.dtos.import_dtos.parts.PartWrapperDtoInput;
import app.repositories.PartRepo;
import app.repositories.SupplierRepo;
import app.serializers.JsonSerializer;
import app.serializers.XmlSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartService {

    private final PartRepo partRepo;
    private final SupplierRepo supplierRepo;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;

    @Autowired
    public PartService(PartRepo partRepo, SupplierRepo supplierRepo, XmlSerializer xmlSerializer) {
        this.partRepo = partRepo;
        this.supplierRepo = supplierRepo;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    public void importParts(String fileName) throws JAXBException {
        List<Supplier> suppliers = this.supplierRepo.findAll();
        PartWrapperDtoInput partWrapperDtoInput = this.xmlSerializer.deserialize(PartWrapperDtoInput.class, fileName);
        Random random = new Random();
        for (PartDtoImport partDtoImport : partWrapperDtoInput.getPartDtoImports()) {
            Part part = this.mapper.map(partDtoImport, Part.class);
            int supplierId = random.nextInt(suppliers.size());
            part.setSupplier(suppliers.get(supplierId));
            suppliers.get(supplierId).getParts().add(part);
            this.partRepo.save(part);
        }
    }
}
