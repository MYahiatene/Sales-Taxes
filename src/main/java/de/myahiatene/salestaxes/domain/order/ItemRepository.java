package de.myahiatene.salestaxes.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

public Item findItemByName(String name);
public List<Item> findAll();
}
