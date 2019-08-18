package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.ProductRequestDto;
import uet.k59t.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(productService.findAllProduct(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/{category_id}")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto, @PathVariable @NotNull Long categoryId) {
        productService.createNewProduct(productRequestDto, categoryId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable @NotNull Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ProductRequestDto productRequestDto, @PathVariable Long id) {
        productService.updateProduct(productRequestDto, id);
        return ResponseEntity.ok().build();
    }
}
