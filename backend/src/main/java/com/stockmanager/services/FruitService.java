package com.stockmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stockmanager.dtos.CreateFruitRequest;
import com.stockmanager.dtos.FruitResponse;
import com.stockmanager.dtos.UpdateFruitRequest;
import com.stockmanager.entities.Fruit;
import com.stockmanager.exceptions.DuplicateSkuException;
import com.stockmanager.repositories.FruitRepository;


@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    
    private void validateSkuIsAvailable(String sku) {
        if(fruitRepository.existsBySku(sku)) {
            throw new DuplicateSkuException(
                "Exist one Fruit with the SKU: " + sku
            );
        }

    }


    public  FruitResponse createFruit(CreateFruitRequest request) {
        validateSkuIsAvailable(request.getSku());


        Fruit fruit = new Fruit(
            request.getSku(), 
            request.getName(), 
            request.getVariety(), 
            request.getOrigin(),
            request.getPricePerKg(), 
            request.getQuantityInStock(), 
            request.getMinimumStock()
        );
        
        Fruit savedFruit = fruitRepository.save(fruit);

        return new FruitResponse(
            savedFruit.getId(),
            savedFruit.getSku(),
            savedFruit.getName(),
            savedFruit.getVariety(),
            savedFruit.getOrigin(),
            savedFruit.getPricePerKg(),
            savedFruit.getQuantityInStock(),
            savedFruit.getMinimumStock()
        );
    }


    public List<FruitResponse> getAllFruits() {

        return fruitRepository.findAll()
            .stream()
            .map(fruit -> new FruitResponse(
                fruit.getId(), 
                fruit.getSku(),
                fruit.getName(),
                fruit.getVariety(),
                fruit.getOrigin(),
                fruit.getPricePerKg(),
                fruit.getQuantityInStock(),
                fruit.getMinimumStock()
            ))
            .toList();
    }


    public Optional<FruitResponse> getFruitById(Long id) {

        return fruitRepository.findById(id)
            .map(fruit -> new FruitResponse(
                fruit.getId(),
                fruit.getSku(),
                fruit.getName(),
                fruit.getVariety(),
                fruit.getOrigin(),
                fruit.getPricePerKg(),
                fruit.getQuantityInStock(),
                fruit.getMinimumStock()
            ));
    }

    public Optional<FruitResponse> updateFruit(Long id, UpdateFruitRequest request) {

        Optional <Fruit> optionalFruit = fruitRepository.findById(id);

        if(optionalFruit.isEmpty()){
            return Optional.empty();
        }

        Fruit fruit = optionalFruit.get();

        if (!fruit.getSku().equals(request.getSku())
                && fruitRepository.existsBySku(request.getSku())) {
                throw new DuplicateSkuException( 
                    "Já existe uma fruta registada com o SKU: " + request.getSku()
                );
        }

        fruit.setSku(request.getSku());
        fruit.setName(request.getName());
        fruit.setVariety(request.getVariety());
        fruit.setOrigin(request.getOrigin());
        fruit.setPricePerKg(request.getPricePerKg());
        fruit.setQuantityInStock(request.getQuantityInStock());
        fruit.setMinimumStock(request.getMinimumStock());

        Fruit savedFruit = fruitRepository.save(fruit);

        return Optional.of( new FruitResponse(
            savedFruit.getId(),
            savedFruit.getSku(),
            savedFruit.getName(),
            savedFruit.getVariety(),
            savedFruit.getOrigin(),
            savedFruit.getPricePerKg(),
            savedFruit.getQuantityInStock(),
            savedFruit.getMinimumStock()
        ));
    }


    public boolean deleteFruit(Long id) {

        if(fruitRepository.existsById(id)) {
            fruitRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
