package de.myahiatene.salestaxes.domain.order;

import de.myahiatene.salestaxes.domain.order.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {
    @Mock
    ItemService itemService;

    @Test
    void createItemTest() {

    }

    @Test
    void calculatePriceWithTaxTest() {

    }

    @Test
    void calculateTotalTaxImportedOnly() {
        //given
        boolean importTax = true;
        boolean basicTax = false;
        //when
        BigDecimal totalTax = itemService.calculateTotalTax(basicTax, importTax);
        //then
        assertThat(totalTax).isEqualTo(BigDecimal.valueOf(1.05));
    }

}
