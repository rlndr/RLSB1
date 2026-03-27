package tech.lander.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.lander.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepoNew extends MongoRepository<Product, String> {
    public List<Product> findByDescription(String description);

    public List<Product> findByStatus(String status);

    List<Product> findAll();

    Optional<Product> findById(String id);

    void save(Product product);
}
