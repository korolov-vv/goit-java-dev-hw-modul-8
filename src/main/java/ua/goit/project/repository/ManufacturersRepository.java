package ua.goit.project.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.goit.project.model.entity.Manufacturer;

import java.util.Optional;

public interface ManufacturersRepository extends Repository<Manufacturer> {

    @Override
    @Query("select m from Manufacturer m where m.manufacturerName=?1")
    Optional<Manufacturer> findByUniqueValue(String value);

    @Override
    @Modifying
    @Query("delete from Manufacturer m where m.manufacturerName=?1")
    void deleteByUniqueValue(String value);
}