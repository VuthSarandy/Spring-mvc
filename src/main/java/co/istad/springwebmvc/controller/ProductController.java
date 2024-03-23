package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    ResponseEntity<?> findProducts(@RequestParam(required = false, defaultValue = "") String name,
                                @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return new ResponseEntity<>(Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        ),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    Map<String, Object> findProductById(@PathVariable Integer id) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductById(id)
        );
    }

    @GetMapping("/uuid/{uuid}")
    Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductByUuid(uuid)
        );
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        System.out.println(request);
        productService.createNewProduct(request);
    }
    @PutMapping("/{uuid}")
    void editProductByUuid (@PathVariable String uuid,
                            @RequestBody ProductEditRequest request){
        productService.editProductByUuid(request,uuid);
    }

//    first style delete by Uuid

//    @DeleteMapping("/{uuid}")
//    void deleteProductByUuid(@PathVariable String uuid){
//        productService.deleteProductByUuid(uuid);
//    }

//    Second style delete by Uuid

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }


}
