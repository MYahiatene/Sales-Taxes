package de.myahiatene.salestaxes.domain.order;

import de.myahiatene.salestaxes.domain.order.Item;
import de.myahiatene.salestaxes.domain.order.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @AfterEach
    void tearDown() {
        itemRepository.deleteAll();
    }

    @Test
    void checkIfItemFoundByName() {
        //given
        Item item = new Item("Buch", false, false, BigDecimal.ONE, BigDecimal.TEN);
        itemRepository.save(item);
        //when
        Item foundItem = itemRepository.findItemByName("Buch");

        //then
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    void checkIfItemNotFoundByName() {
        //given
        Item item = new Item("Buch", false, false, BigDecimal.ONE, BigDecimal.TEN);

        //when
        Item foundItem = itemRepository.findItemByName("Buch");

        //then
        assertThat(foundItem).isNull();
    }
}
