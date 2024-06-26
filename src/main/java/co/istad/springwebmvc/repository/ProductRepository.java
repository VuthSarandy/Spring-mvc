package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByUuid(String uuid);

    Product findProductByUuid(String uuid);

    void deleteByUuid(String uuid);
}
