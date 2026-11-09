package com.stockmanager.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "fruits")  
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;

    @NotBlank
    @Size(max = 30) 
    @Column(nullable = false, unique = true, length = 30)
    private String sku;

    @NotBlank
    @Size(max = 100)  
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 100) 
    @Column(length = 100)
    private String variety;

    @Size(max = 100) 
    @Column(length = 100)
    private String origin;

    @NotNull
    @DecimalMin("0.01") 
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerKg;

    @NotNull
    @DecimalMin("0.000")
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal quantityInStock;

    @NotNull
    @DecimalMin("0.000")
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal minimumStock;

    protected Fruit(){}

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public BigDecimal getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(BigDecimal quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public BigDecimal getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(BigDecimal minimumStock) {
        this.minimumStock = minimumStock;
    }


}
