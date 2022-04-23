package de.myahiatene.salestaxes.domain.order.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public Item addItem(final String name, int amount, final boolean basicTax, final boolean importTax, final BigDecimal price) {

        final BigDecimal priceWithTax = calculatePriceWithTax(price, basicTax, importTax);
        final BigDecimal salesTax = priceWithTax.subtract(price);
        final Item item = new Item(name, amount, basicTax, importTax, price, salesTax, priceWithTax);
        return itemRepository.save(item);
    }

    @Override
    public BigDecimal calculatePriceWithTax(final BigDecimal price, final boolean basicTax, final boolean importTax) {
        BigDecimal basicTaxVal = BigDecimal.valueOf(0);
        BigDecimal importTaxVal = BigDecimal.valueOf(0);
        if (basicTax) {
            basicTaxVal = basicTaxVal.add(BigDecimal.valueOf(0.1));
        }
        if (importTax) {
            importTaxVal = importTaxVal.add(BigDecimal.valueOf(0.05));
        }
        final BigDecimal calculatedBasicTaxValue = round(price.multiply(basicTaxVal));
        final BigDecimal calculatedImportTaxValue = round(price.multiply(importTaxVal));
        return calculatedBasicTaxValue.add(calculatedImportTaxValue).add(price);


    }

    public BigDecimal round(final BigDecimal price) {
        return BigDecimal.valueOf(Math.ceil(price.multiply(BigDecimal.valueOf(20.0)).doubleValue()) / 20.0).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTotalTax(final boolean basicTax, final boolean importTax) {
        BigDecimal totalTax = BigDecimal.valueOf(1.00);
        if (basicTax) {
            totalTax = totalTax.add(BigDecimal.valueOf(0.1));
        }
        if (importTax) {
            totalTax = totalTax.add(BigDecimal.valueOf(0.05));
        }
        return totalTax;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void deleteBasket() {
        itemRepository.deleteAll();

    }


}
