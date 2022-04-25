package de.myahiatene.salestaxes.domain.order.item;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * BasketItem entity class.
 */
@AllArgsConstructor
@Data
public class BasketItem {
    //name,amount,basicTax,importTax,price,salesTax,priceWithTax
    private final String name;
    private final int amount;

    private final BigDecimal totalSalesTax;

    private final BigDecimal price;
    private final BigDecimal priceWithTax;

}
