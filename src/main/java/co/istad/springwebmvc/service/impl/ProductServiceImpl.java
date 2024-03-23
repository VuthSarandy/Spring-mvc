package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.ProductRepository;
import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<ProductResponse> findProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new  ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not been found!"
        ));
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty());
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        if (!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        Product product = productRepository.findProductByUuid(uuid);
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty());
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setImportedDate(LocalDateTime.now());
        product.setStatus(true);
        productRepository.save(product);

    }

    @Override
    public ProductResponse editProductByUuid(ProductEditRequest request, String uuid) {
        if (!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        Product product = productRepository.findProductByUuid(uuid);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        productRepository.save(product);
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(),product.getQty());

    }

    @Override
    public void deleteProductByUuid(String uuid) {
        log.info("Product Uuid{}",uuid);
        if (!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not been found!"
            );
        }
        Product product = this.productRepository.findProductByUuid(uuid);
        productRepository.deleteById(product.getId());

    }
}
