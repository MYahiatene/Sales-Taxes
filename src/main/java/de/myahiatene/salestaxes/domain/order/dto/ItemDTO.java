package de.myahiatene.salestaxes.domain.order.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Item DTO Class.
 */
@Data
public class ItemDTO {
    private String name;

    private int amount;

    private boolean basicTax;

    private boolean importTax;

    private BigDecimal price;

}
