package de.myahiatene.salestaxes.domain;

import de.myahiatene.salestaxes.domain.order.item.ItemService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Class to initialize Database with default values if necessary.
 */
@Service
public class InitializeDatabaze implements InitializingBean {
    /**
     * Itemservice interface to manipulate database.
     */
    public final ItemService itemService;

    /**
     * Constructor of InitializeDatabase.
     *
     * @param itemService Itemservice for item related methods.
     */
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
