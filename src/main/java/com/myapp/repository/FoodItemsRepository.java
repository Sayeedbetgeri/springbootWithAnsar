package com.myapp.repository;

import com.myapp.dto.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FoodItemsRepository extends MongoRepository<FoodItem,Integer>{

    @Transactional()
    public FoodItem findByItemName(String itemName);
    public List<FoodItem> findByAvailable(Boolean available);
    public List<FoodItem> findByItemNameAndAvailable(String itemName, Boolean available);

}
