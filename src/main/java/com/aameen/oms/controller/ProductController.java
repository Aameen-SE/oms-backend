package com.aameen.oms.controller;

import com.aameen.oms.dto.ApiResponse;
import com.aameen.oms.dto.ProductDTO;
import com.aameen.oms.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {

        ProductDTO product = productService.createProduct(dto);

        return ApiResponse.<ProductDTO>builder()
                .success(true)
                .message("Product created successfully")
                .data(product)
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> products = productService.getAllProducts();

        return ApiResponse.<List<ProductDTO>>builder()
                .success(true)
                .message("Products fetched successfully")
                .data(products)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> getProductById(@PathVariable Long id) {

        ProductDTO product = productService.getProductById(id);

        return ApiResponse.<ProductDTO>builder()
                .success(true)
                .message("Product fetched successfully")
                .data(product)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<ProductDTO> updateProduct(@PathVariable Long id,
                                                 @Valid @RequestBody ProductDTO dto) {

        ProductDTO updated = productService.updateProduct(id, dto);

        return ApiResponse.<ProductDTO>builder()
                .success(true)
                .message("Product updated successfully")
                .data(updated)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return ApiResponse.builder()
                .success(true)
                .message("Product deleted successfully")
                .data(null)
                .build();
    }

}