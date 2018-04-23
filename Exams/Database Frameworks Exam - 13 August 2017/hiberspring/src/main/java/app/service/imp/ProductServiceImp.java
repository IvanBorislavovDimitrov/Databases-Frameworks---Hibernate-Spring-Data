package app.service.imp;

import app.domain.dtos.xml.importDtos.ProductImportXMLDto;
import app.domain.entities.Branch;
import app.domain.entities.Product;
import app.repositories.ProductRepository;
import app.service.api.BranchService;
import app.service.api.ProductService;
import app.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final BranchService branchService;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, BranchService branchService) {
        this.productRepository = productRepository;
        this.branchService = branchService;
        this.mapper = new ModelMapper();
    }

    @Override
    public void create(ProductImportXMLDto productDto) {
        if (! ValidatorUtil.isValid(productDto)) {
            throw new IllegalArgumentException();
        }

        Branch branch = this.branchService.findByName(productDto.getBranchName());
        if (branch == null) {
            throw new IllegalArgumentException();
        }

        Product product = this.mapper.map(productDto, Product.class);
        product.setBranch(branch);

        this.productRepository.saveAndFlush(product);
    }
}
