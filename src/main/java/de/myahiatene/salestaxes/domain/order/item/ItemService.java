package de.myahiatene.salestaxes.domain.order.item;

import java.math.BigDecimal;
import java.util.List;

/**
 * Item service interface.
 */
public interface ItemService {
    BasketItem addItem(String name, int amount, boolean basicTax, boolean importTax, BigDecimal price);

    BigDecimal calculatePriceWithTax(BigDecimal price, boolean basicTax, boolean importTax);

    BigDecimal calculateTotalTax(boolean basicTax, boolean importTax);

    List<Item> getAllItems();

    void deleteBasket();


}
