package ua.goit.project.service;

import org.springframework.stereotype.Service;
import ua.goit.project.exceptions.ObjectAlreadyExistException;
import ua.goit.project.model.entity.Product;
import ua.goit.project.model.repository.ProductRepository;
import ua.goit.project.model.repository.Repository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements MyService<Product> {

    private final Repository<Product> repository;

    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Product save(Product product) {
        if(repository.findByUniqueValue(product.getProductName()).isPresent()){
            throw new ObjectAlreadyExistException(String.format("Product with specified name already exist %s",
                    product.getProductName()));
        }
        return repository.save(product);
    }

    @Override
    public List<Product> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> read(String value) {
        return repository.findByUniqueValue(value);
    }

    @Override
    public void delete(String value) {
        repository.deleteByUniqueValue(value);
    }
}
