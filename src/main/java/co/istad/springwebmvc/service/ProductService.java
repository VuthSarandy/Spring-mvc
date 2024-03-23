package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findProducts();

    ProductResponse findProductById(Integer id);

    ProductResponse findProductByUuid(String uuid);

    void createNewProduct(ProductCreateRequest request);

    ProductResponse editProductByUuid(ProductEditRequest request, String uuid);
    void  deleteProductByUuid(String uuid);
}

