package ua.goit.project.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @NotEmpty
    @Column(name = "product_name", unique = true)
    private String productName;
    @NotEmpty
    @Column(name = "price")
    private BigDecimal productPrice;
    @ManyToOne
    @JoinColumn(name="manufacturer_id", nullable=false)
    private Manufacturer manufacturer;

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productId == product.productId &&
                productName.equals(product.productName) &&
                productPrice.equals(product.productPrice);
    }
}
