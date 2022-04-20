package de.myahiatene.salestaxes.domain.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ItemServiceImpl implements ItemService {

    final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(String name, boolean basicTax, boolean importTax, BigDecimal price) {
        BigDecimal priceWithTax = calculatePriceWithTax(price, basicTax, importTax);
        Item item = new Item(name, basicTax, importTax, price, priceWithTax);
        return itemRepository.save(item);
    }

    @Override
    public BigDecimal calculatePriceWithTax(BigDecimal price, boolean basicTax, boolean importTax) {
        BigDecimal totalTax = calculateTotalTax(basicTax, importTax);
        return totalTax
            .multiply(price)
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.UP);
    }

    @Override
    public BigDecimal calculateTotalTax(boolean basicTax, boolean importTax) {
        return new BigDecimal(1)
            .add(BigDecimal.valueOf(basicTax ? 0.1 : 0))
            .add(BigDecimal.valueOf(basicTax ? 0.05 : 0));
    }


}
