package uet.k59t.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uet.k59t.dto.ProductDto;
import uet.k59t.dto.ProductRequestDto;

import uet.k59t.model.Product;
import uet.k59t.repository.ProductRepository;

import javax.validation.constraints.NotNull;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    public Page<ProductDto> findAllProduct(Pageable pageable) {
        Page<Product> leadPage = productRepository.findAll(pageable);
        return leadPage.map(e -> {
            ProductDto productDto = modelMapper.map(e, ProductDto.class);
            productDto.setCategoryId(e.getCategory().getId());
            return productDto;
        });
    }

    public ProductDto findById(Long id) {
        Product product = findExistedProduct(id);
        return convertToDto(product);
    }

    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductRequestDto productDto, @NotNull Long categoryId) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(categoryService.findCategory(categoryId));
        productRepository.save(product);
    }

    public void updateProduct(ProductRequestDto productRequestDto, Long id) {
        Product product = findExistedProduct(id);
        modelMapper.map(productRequestDto, product);
        productRepository.save(product);
    }

    public Product findExistedProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found", new Error()));
        return product;
    }
}
