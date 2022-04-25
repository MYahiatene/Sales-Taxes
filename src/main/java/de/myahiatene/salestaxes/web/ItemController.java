package de.myahiatene.salestaxes.web;

import de.myahiatene.salestaxes.domain.order.dto.ItemDTO;
import de.myahiatene.salestaxes.domain.order.item.BasketItem;
import de.myahiatene.salestaxes.domain.order.item.ItemServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * Restcontroller for interacting with the item repository.
 */
@RestController
@CrossOrigin
@RequestMapping("/v1/api")
public class ItemController {
    /**
     * Itemservice to have access to item related methods.
     */
    public final ItemServiceImpl itemServiceImpl;

    public ItemController(final ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

    @PostMapping("/addItem")
    public BasketItem createItem(final @RequestBody ItemDTO itemDTO) {
        return itemServiceImpl.addItem(itemDTO.getName(),
            itemDTO.getAmount(), itemDTO.isBasicTax(), itemDTO.isImportTax(), itemDTO.getPrice());
    }

    @GetMapping("/deleteBasket")
    public void deleteBasket() {
        itemServiceImpl.deleteBasket();
    }
}
