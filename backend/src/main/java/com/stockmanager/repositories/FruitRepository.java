package com.stockmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmanager.entities.Fruit;

public interface FruitRepository  extends JpaRepository<Fruit, Long> {
    

    boolean existsBySku(String sku);
}
