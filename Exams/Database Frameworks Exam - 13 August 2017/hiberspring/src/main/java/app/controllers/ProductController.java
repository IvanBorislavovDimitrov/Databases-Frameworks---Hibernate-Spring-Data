package app.controllers;

import app.domain.dtos.xml.importDtos.ProductImportXMLDto;
import app.domain.dtos.xml.importDtos.ProductWrapperImportXMLDto;
import app.parsers.XmlParser;
import app.service.api.ProductService;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    private final ProductService productService;
    private final XmlParser xmlParser;

    public ProductController(ProductService productService, XmlParser xmlParser) {
        this.productService = productService;
        this.xmlParser = xmlParser;
    }

    public String importProductsFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        ProductWrapperImportXMLDto products = this.xmlParser.read(ProductWrapperImportXMLDto.class, xmlContent);

        for (ProductImportXMLDto product : products.getProducts()) {
            try {
                this.productService.create(product);
                sb.append(String.format("Successfully imported Product %s.\r\n", product.getName()));
            } catch (Exception e) {
                sb.append("Error: Invalid data.\r\n");
            }
        }

        return sb.toString();
    }

}
