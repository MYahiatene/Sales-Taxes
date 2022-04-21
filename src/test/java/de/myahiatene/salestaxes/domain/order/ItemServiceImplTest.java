package de.myahiatene.salestaxes.domain.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
    @Mock
    private ItemRepository itemRepository;
    private ItemServiceImpl itemServiceImpl;

    private ItemService itemService;

    @BeforeEach
    void setUp() {

        itemServiceImpl = new ItemServiceImpl(itemRepository, itemService);
    }

    @Test
    void checkIfCalculateTotalTaxWithBasicTaxAndImportTax() {
        //given
        boolean basicTax = true;
        boolean importTax = true;
        //when
        BigDecimal totalTax = itemServiceImpl.calculateTotalTax(basicTax, importTax);
        //then
        assertThat(totalTax).isEqualTo(BigDecimal.valueOf(1.15));

    }
    @Test
    void checkIfCalculateTotalTaxWithBasicTaxAndWithoutImportTax() {
        //given
        boolean basicTax = true;
        boolean importTax = false;
        //when
        BigDecimal totalTax = itemServiceImpl.calculateTotalTax(basicTax, importTax);
        //then
        assertThat(totalTax).isEqualTo(BigDecimal.valueOf(1.10));

    }
    @Test
    void checkIfCalculateTotalTaxWithoutBasicTaxAndImportTax() {
        //given
        boolean basicTax = false;
        boolean importTax = true;
        //when
        BigDecimal totalTax = itemServiceImpl.calculateTotalTax(basicTax, importTax);
        //then
        assertThat(totalTax).isEqualTo(BigDecimal.valueOf(1.05));

    }
    @Test
    void canCalculatePriceWithoutBasicTaxAndWithImportTax() {
        //given
        boolean basicTax = false;
        boolean importTax =true;
        BigDecimal price = BigDecimal.valueOf(11.25);
        //when
        BigDecimal priceWithTax = itemServiceImpl.calculatePriceWithTax(price,  basicTax,importTax);
        //then
        assertThat(priceWithTax).isEqualTo(BigDecimal.valueOf(11.85));
    }
    @Test
    void canCalculatePriceWithBasicTaxAndWithImportTax() {
        //given
        boolean basicTax = true;
        boolean importTax =true;
        BigDecimal price = BigDecimal.valueOf(27.99);
        //when
        BigDecimal priceWithTax = itemServiceImpl.calculatePriceWithTax(price, basicTax,importTax);
        //then
        assertThat(priceWithTax).isEqualTo(BigDecimal.valueOf(32.19));
    }
    @Test
    void canCalculatePriceWithBasicTaxAndWithoutImportTax() {
        //given
        boolean basicTax = true;
        boolean importTax =false;
        BigDecimal price = BigDecimal.valueOf(18.99);
        //when
        BigDecimal priceWithTax = itemServiceImpl.calculatePriceWithTax(price, basicTax,importTax);
        //then
        assertThat(priceWithTax).isEqualTo(BigDecimal.valueOf(20.89));
    }
    @Test
    @Disabled
    void canAddItem() {
        //given

        Item item = new Item("Harry Potter", false, false, BigDecimal.valueOf(20.0), null);
        //when

        //then
    }

    @Test
    void canGetAllItems() {
        //when
        itemServiceImpl.getAllItems();
        //then
        verify(itemRepository).findAll();
    }
}
