package com.stockmanager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmanager.dtos.CreateFruitRequest;
import com.stockmanager.dtos.FruitResponse;
import com.stockmanager.dtos.UpdateFruitRequest;
import com.stockmanager.services.FruitService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/fruits")
public class FruitsController {

    private final FruitService fruitService;

    public FruitsController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    
    
    @PostMapping
    public ResponseEntity <FruitResponse> createFruit(
            @Valid @RequestBody CreateFruitRequest request ) {
            
        FruitResponse createdFruit = fruitService.createFruit(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdFruit);

    }


    @GetMapping
    public ResponseEntity<List<FruitResponse>> getAllFruits() {

        List<FruitResponse> fruits = fruitService.getAllFruits();

        return ResponseEntity.ok(fruits);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity <FruitResponse> getFruitById(@PathVariable Long id) {

        return fruitService.getFruitById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());   
    }

    @PutMapping("/{id}")
    public ResponseEntity<FruitResponse> updateFruit(
        @PathVariable Long id,
        @Valid @RequestBody  UpdateFruitRequest request) {

        return fruitService.updateFruit(id, request)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable Long id) {

        if(fruitService.deleteFruit(id) == false){
           return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
