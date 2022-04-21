package de.myahiatene.salestaxes.domain.order;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {
    public Item addItem(final String name, final boolean basicTax, final boolean importTax, BigDecimal price);

    public BigDecimal calculatePriceWithTax(BigDecimal price, boolean basicTax , boolean importTax );

    public BigDecimal calculateTotalTax(boolean basicTax,boolean importTax);

    public List<Item> getAllItems();



}
