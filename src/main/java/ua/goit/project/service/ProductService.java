package ua.goit.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.project.model.entity.Product;
import ua.goit.project.repository.ProductRepository;
import ua.goit.project.repository.Repository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IService<Product> {

    private final Repository<Product> productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> readAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> read(String value) {
        return productRepository.findByUniqueValue(value);
    }

    @Override
    @Transactional
    public void delete(String value) {
        productRepository.deleteByUniqueValue(value);
    }
}
