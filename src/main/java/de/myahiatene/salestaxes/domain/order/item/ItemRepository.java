package de.myahiatene.salestaxes.domain.order.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Item findItemByName(String name);

    @Override
    List<Item> findAll();

    void deleteItemByName(String name);
}
