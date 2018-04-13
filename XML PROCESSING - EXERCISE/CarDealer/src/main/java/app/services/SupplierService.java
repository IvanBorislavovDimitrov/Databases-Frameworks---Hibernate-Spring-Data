package app.services;

import app.entitites.Supplier;
import app.entitites.dtos.queries.cars_from_make_toyota.CarInfoWrapperDto;
import app.entitites.dtos.queries.local_suppliers.SupplierShortInfoDto;
import app.entitites.dtos.import_dtos.suppliers.SupplierDtoInput;
import app.entitites.dtos.import_dtos.suppliers.SupplierWrapperInput;
import app.entitites.dtos.queries.local_suppliers.SupplierShortInfoWrapperDto;
import app.repositories.SupplierRepo;
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
public class SupplierService {

    private final SupplierRepo supplierRepo;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo, XmlSerializer xmlSerializer) {
        this.supplierRepo = supplierRepo;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
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
            SupplierShortInfoWrapperDto ssiwd = new SupplierShortInfoWrapperDto();
            ssiwd.setSupplierShortInfoDtos(supplierShortInfoDtos);
            this.xmlSerializer.serialize(ssiwd, fileName);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void importSuppliersFromFile(String fileName) throws JAXBException {
        SupplierWrapperInput suppliers = this.xmlSerializer.deserialize(SupplierWrapperInput.class, fileName);
        for (SupplierDtoInput supplierDtoInput : suppliers.getSupplierDtoInputs()) {
            this.supplierRepo.save(this.mapper.map(supplierDtoInput, Supplier.class));
        }
    }
}
