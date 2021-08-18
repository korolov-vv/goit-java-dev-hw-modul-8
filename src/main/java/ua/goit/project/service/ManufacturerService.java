package ua.goit.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.project.model.entity.Manufacturer;
import ua.goit.project.repository.ManufacturersRepository;
import ua.goit.project.repository.Repository;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService implements IService<Manufacturer> {

    private final Repository<Manufacturer> repository;

    public ManufacturerService(ManufacturersRepository manufacturersRepository) {
        this.repository = manufacturersRepository;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return repository.saveAndFlush(manufacturer);
    }

    @Override
    public List<Manufacturer> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Manufacturer> read(String value) {
        return repository.findByUniqueValue(value);
    }

    @Override
    @Transactional
    public void delete(String value) {
        repository.deleteByUniqueValue(value);
    }
}
