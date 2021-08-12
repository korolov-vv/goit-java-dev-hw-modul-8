package ua.goit.project.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "manufacturers")
@Getter @Setter @NoArgsConstructor
@ToString
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int manufacturerId;
    @NotEmpty
    @Column(name = "manufacturer_name", unique = true)
    private String manufacturerName;
    @OneToMany(mappedBy="manufacturer")
    @ToString.Exclude
    private Set<Product> productsSet;

    @Override
    public int hashCode() {
        return Objects.hash(manufacturerId, manufacturerName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Manufacturer manufacturer = (Manufacturer) obj;
        return manufacturerId == manufacturer.manufacturerId &&
                manufacturerName.equals(manufacturer.manufacturerName);
    }
}
