package de.myahiatene.salestaxes.domain.order.item;

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
        final String name = "Buch";
        final Item item = new Item(name, 4, false, false, BigDecimal.ONE, null, BigDecimal.TEN);
        itemRepository.save(item);
        //when
        final Item foundItem = itemRepository.findItemByName(name);

        //then
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    void checkIfItemNotFoundByName() {
        //given
        // Item item = new Item("Buch", false, false, BigDecimal.ONE, BigDecimal.TEN);

        //when
        final Item foundItem = itemRepository.findItemByName("Buch");

        //then
        assertThat(foundItem).isNull();
    }

    @Test
    void checkIfItemDeleted() {
        //given
        final String name = "Buch";
        final Item item = new Item(name, 1, false, false, BigDecimal.ONE, null, BigDecimal.TEN);
        itemRepository.save(item);
        //when
        itemRepository.deleteItemByName(name);
        final Item foundItem = itemRepository.findItemByName(name);
        //then
        assertThat(foundItem).isNull();
    }
}
