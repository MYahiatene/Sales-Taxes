package de.myahiatene.salestaxes.domain.order.item;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
    @Mock
    private ItemRepository itemRepository;
    private ItemServiceImpl itemServiceImpl;


    @BeforeEach
    void setUp() {

        itemServiceImpl = new ItemServiceImpl(itemRepository);
    }

    @Test
    void checkIfCalculateTotalTaxWithBasicTaxAndImportTax() {
        //given
        final boolean basicTax = true;
        final boolean importTax = true;
        //when
        final BigDecimal totalTax = itemServiceImpl.calculateTotalTax(basicTax, importTax);
        //then
        assertThat(totalTax).isEqualTo(BigDecimal.valueOf(1.15));

    }

    @Test
    void checkIfCalculateTotalTaxWithBasicTaxAndWithoutImportTax() {
        //given
        final boolean basicTax = true;
        final boolean importTax = false;
        //when
        final BigDecimal totalTax = itemServiceImpl.calculateTotalTax(basicTax, importTax);
        //then
        assertThat(totalTax).isEqualTo(BigDecimal.valueOf(1.10));

    }

    @Test
    void checkIfCalculateTotalTaxWithoutBasicTaxAndImportTax() {
        //given
        final boolean basicTax = false;
        final boolean importTax = true;
        //when
        final BigDecimal totalTax = itemServiceImpl.calculateTotalTax(basicTax, importTax);
        //then
        assertThat(totalTax).isEqualTo(BigDecimal.valueOf(1.05));

    }

    @Test
    void canCalculatePriceWithoutBasicTaxAndWithImportTax() {
        //given
        final boolean basicTax = false;
        final boolean importTax = true;
        final BigDecimal price = BigDecimal.valueOf(11.25);
        //when
        final BigDecimal priceWithTax = itemServiceImpl.calculatePriceWithTax(price, basicTax, importTax);
        //then
        assertThat(priceWithTax).isEqualTo(BigDecimal.valueOf(11.85));
    }

    @Test
    void canCalculatePriceWithBasicTaxAndWithImportTax() {
        //given
        final boolean basicTax = true;
        final boolean importTax = true;
        final BigDecimal price = BigDecimal.valueOf(27.99);
        //when
        final BigDecimal priceWithTax = itemServiceImpl.calculatePriceWithTax(price, basicTax, importTax);
        //then
        assertThat(priceWithTax).isEqualTo(BigDecimal.valueOf(32.19));
    }

    @Test
    void canCalculatePriceWithBasicTaxAndWithoutImportTax() {
        //given
        final boolean basicTax = true;
        final boolean importTax = false;
        final BigDecimal price = BigDecimal.valueOf(18.99);
        //when
        final BigDecimal priceWithTax = itemServiceImpl.calculatePriceWithTax(price, basicTax, importTax);
        //then
        assertThat(priceWithTax).isEqualTo(BigDecimal.valueOf(20.89));
    }

    @Test
    void canAddItem() {
        //given

        final Item item = new Item("Harry Potter", 1, false, false, BigDecimal.valueOf(20.0), null, null);
        final BigDecimal priceWithTax = itemServiceImpl.calculatePriceWithTax(item.getPrice(), item.isBasicTax(), item.isImportTax());
        item.setPriceWithTax(priceWithTax);
        //when
        itemRepository.save(item);
        //then
        final ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(itemArgumentCaptor.capture());
        final Item capturedItem = itemArgumentCaptor.getValue();
        AssertionsForClassTypes.assertThat(capturedItem).isEqualTo(item);
    }

    @Test
    void canGetAllItems() {
        //when
        itemServiceImpl.getAllItems();
        //then
        verify(itemRepository).findAll();
    }
}
