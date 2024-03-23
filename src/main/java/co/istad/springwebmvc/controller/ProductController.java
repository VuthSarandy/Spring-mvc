package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> findProducts(){
        return productService.findProducts();
    }
    @GetMapping("/{id}")
    ProductResponse findProductById(@PathVariable Integer id ){
        return productService.findProductById(id);
    }
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createNewProduct(request);
    }
    @PutMapping("/{uuid}")
    ProductResponse editProductByUuid(@Valid @RequestBody ProductEditRequest request,@PathVariable String uuid ){
        return productService.editProductByUuid(request,uuid);
    }
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }
}
