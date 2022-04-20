package de.myahiatene.salestaxes.domain.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class Item {
    @Id
    @Column
    private String name;
    @Column
    private boolean basicTax;
    @Column
    private boolean importTax;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal priceWithTax;


    public Item(String name, boolean basicTax, boolean importTax, BigDecimal price, BigDecimal priceWithTax) {
        this.name = name;
        this.basicTax = basicTax;
        this.importTax = importTax;
        this.price = price;
        this.priceWithTax = priceWithTax;
    }


}
