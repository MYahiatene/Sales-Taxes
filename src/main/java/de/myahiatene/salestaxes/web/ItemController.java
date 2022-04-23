package de.myahiatene.salestaxes.web;

import de.myahiatene.salestaxes.domain.order.dto.ItemDTO;
import de.myahiatene.salestaxes.domain.order.item.Item;
import de.myahiatene.salestaxes.domain.order.item.ItemServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/api")
public class ItemController {

    public final ItemServiceImpl itemServiceImpl;

    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

    @PostMapping("/addItem")
    public Item createItem(final @RequestBody ItemDTO itemDTO) {
        return itemServiceImpl.addItem(itemDTO.getName(),
            itemDTO.getAmount(), itemDTO.isBasicTax(), itemDTO.isImportTax(), itemDTO.getPrice());
    }

    @GetMapping("/deleteBasket")
    public void deleteBasket() {
        itemServiceImpl.deleteBasket();
    }
}
