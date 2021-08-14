package ua.goit.project.service;

import org.springframework.stereotype.Service;
import ua.goit.project.exceptions.ObjectAlreadyExistException;
import ua.goit.project.model.entity.Manufacturer;
import ua.goit.project.model.repository.ManufacturersRepository;
import ua.goit.project.model.repository.Repository;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService implements MyService<Manufacturer> {

    private final Repository<Manufacturer> repository;

    public ManufacturerService(ManufacturersRepository manufacturersRepository) {
        this.repository = manufacturersRepository;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        if(repository.findByUniqueValue(manufacturer.getManufacturerName()).isPresent()){
            throw new ObjectAlreadyExistException(String.format("Manufacturer with specified name already exist %s",
                    manufacturer.getManufacturerName()));
        }
        return repository.save(manufacturer);
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
    public void delete(String value) {
        repository.deleteByUniqueValue(value);
    }
}
