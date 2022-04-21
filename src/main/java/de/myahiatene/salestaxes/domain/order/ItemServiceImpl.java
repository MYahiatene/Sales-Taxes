package de.myahiatene.salestaxes.domain.order;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    final ItemRepository itemRepository;
    final ItemService itemService;


    @Override
    public Item addItem(String name, boolean basicTax, boolean importTax, BigDecimal price) {

        BigDecimal priceWithTax = calculatePriceWithTax(price, basicTax,importTax);
        Item item = new Item(name, basicTax, importTax, price, priceWithTax);

        return itemRepository.save(item);
    }

    @Override
    public BigDecimal calculatePriceWithTax(BigDecimal price, boolean basicTax, boolean importTax) {
        BigDecimal basicTaxVal = BigDecimal.valueOf(0);
        BigDecimal importTaxVal = BigDecimal.valueOf(0);
        if (basicTax) {
            basicTaxVal = basicTaxVal.add(BigDecimal.valueOf(0.1));
        }
        if (importTax) {
            importTaxVal = importTaxVal.add(BigDecimal.valueOf(0.05));
        }
        BigDecimal tmp = round(price.multiply(basicTaxVal));
        BigDecimal tmp2 = round(price.multiply(importTaxVal));
        return tmp.add(tmp2).add(price);


    }

    public BigDecimal round(BigDecimal price) {
        return price.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.UNNECESSARY);
    }

    @Override
    public BigDecimal calculateTotalTax(boolean basicTax, boolean importTax) {
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


}
