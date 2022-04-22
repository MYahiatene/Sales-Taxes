package de.myahiatene.salestaxes.domain;

import de.myahiatene.salestaxes.domain.order.item.ItemService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InitializeDatabaze implements InitializingBean {

    final ItemService itemService;

    @Autowired
    public InitializeDatabaze(final ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        //todo:add all items before for better testing
      /*  this.itemService.addItem("Die Leiden des jungen Werther", 1,
            false, false, BigDecimal.valueOf(11.25));
*/
    }
}
