package app.service.api;

import app.domain.dtos.xml.importDtos.ProductImportXMLDto;

public interface ProductService {

    void create(ProductImportXMLDto productDto);
}
