package de.myahiatene.salestaxes.domain;

import de.myahiatene.salestaxes.domain.order.ItemService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class InitializeDatabaze implements InitializingBean {

    final ItemService itemService;

    @Autowired
    public InitializeDatabaze(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        this.itemService.addItem("Die Leiden des jungen Werther",
            false, false, BigDecimal.valueOf(11.25));

    }
}
