package de.myahiatene.salestaxes.domain.order.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Item entity class.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    @Id
    @Column
    private String name;
    @Column
    private int amount;
    @Column
    private boolean basicTax;
    @Column
    private boolean importTax;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal salesTaxes;

    @Column
    private BigDecimal priceWithTax;


}
