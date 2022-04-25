package de.myahiatene.salestaxes.domain.order.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Implementation Class for the Item Service interface.
 */
@Service
public class ItemServiceImpl implements ItemService {
    private static final double BASICTAXNO = 0.1;
    private static final double IMPORTAXNO = 0.05;
    private static final double TWENTY = 20.0;
    /**
     * Item repository for crud operations on item entities.
     */
    @Autowired
    public final ItemRepository itemRepository;


    public ItemServiceImpl(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public BasketItem addItem(final String name, final int amount, final boolean basicTax,
                              final boolean importTax, final BigDecimal price) {

        final BigDecimal priceWithTax = calculatePriceWithTax(price, basicTax, importTax);
        final BigDecimal salesTax = priceWithTax.subtract(price);
        final Item item = new Item(name, amount, basicTax, importTax, price, salesTax, priceWithTax);
        itemRepository.save(item);
        return new BasketItem(item.getName(),
            item.getAmount(),
            item.getSalesTaxes().multiply(
                BigDecimal.valueOf(item.getAmount())).setScale(2, RoundingMode.HALF_UP), item.getPrice(),
            priceWithTax.multiply(BigDecimal.valueOf(item.getAmount())));
    }

    @Override
    public BigDecimal calculatePriceWithTax(final BigDecimal price, final boolean basicTax, final boolean importTax) {
        BigDecimal basicTaxVal = BigDecimal.valueOf(0);
        BigDecimal importTaxVal = BigDecimal.valueOf(0);
        if (basicTax) {
            basicTaxVal = basicTaxVal.add(BigDecimal.valueOf(BASICTAXNO));
        }
        if (importTax) {
            importTaxVal = importTaxVal.add(BigDecimal.valueOf(IMPORTAXNO));
        }
        final BigDecimal calculatedBasicTaxValue = round(price.multiply(basicTaxVal));
        final BigDecimal calculatedImportTaxValue = round(price.multiply(importTaxVal));
        return calculatedBasicTaxValue.add(calculatedImportTaxValue).add(price);


    }

    /**
     * Method for rounding a value to the nearest 0.05.
     *
     * @param price The price value that needs to be rounded to the nearest 0.05.
     * @return the rounded price value.
     */
    public BigDecimal round(final BigDecimal price) {
        return BigDecimal.valueOf(Math.ceil(price
                .multiply(BigDecimal.valueOf(TWENTY)).doubleValue()) / TWENTY)
            .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTotalTax(final boolean basicTax, final boolean importTax) {
        BigDecimal totalTax = BigDecimal.valueOf(1.00);
        if (basicTax) {
            totalTax = totalTax.add(BigDecimal.valueOf(BASICTAXNO));
        }
        if (importTax) {
            totalTax = totalTax.add(BigDecimal.valueOf(IMPORTAXNO));
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
