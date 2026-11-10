package com.stockmanager.dtos;

import java.math.BigDecimal;

public class FruitResponse {
    
    private Long id;
    private String sku;
    private String name;
    private String variety;
    private String origin;
    private BigDecimal pricePerKg;
    private BigDecimal quantityInStock;
    private BigDecimal minimumStock;

    
    
    public FruitResponse(Long id, String sku, String name, String variety, String origin, BigDecimal pricePerKg,
            BigDecimal quantityInStock, BigDecimal minimumStock) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.variety = variety;
        this.origin = origin;
        this.pricePerKg = pricePerKg;
        this.quantityInStock = quantityInStock;
        this.minimumStock = minimumStock;
    }

    
    public Long getId() {
        return id;
    }
    public String getSku() {
        return sku;
    }
    public String getName() {
        return name;
    }
    public String getVariety() {
        return variety;
    }
    public String getOrigin() {
        return origin;
    }
    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }
    public BigDecimal getQuantityInStock() {
        return quantityInStock;
    }
    public BigDecimal getMinimumStock() {
        return minimumStock;
    }

    

}
