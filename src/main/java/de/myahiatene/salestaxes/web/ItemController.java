package de.myahiatene.salestaxes.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.myahiatene.salestaxes.domain.order.dto.ItemDTO;
import de.myahiatene.salestaxes.domain.order.item.ItemServiceImpl;
import de.myahiatene.salestaxes.domain.order.item.Item;
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
    System.out.println(itemDTO);
       Item item= itemServiceImpl.addItem(itemDTO.getName(),
            itemDTO.getAmount(), itemDTO.isBasicTax(), itemDTO.isImportTax(), itemDTO.getPrice());
        return item;
    }

    @GetMapping("/createReceipt")
    public String createReceipt() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(itemServiceImpl.createReceipt());
    }
}
