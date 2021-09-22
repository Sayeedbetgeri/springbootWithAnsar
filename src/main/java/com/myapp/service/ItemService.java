package com.myapp.service;

import com.myapp.dto.FoodItem;
import com.myapp.repository.FoodItemRepositoryImpl;
import com.myapp.repository.FoodItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    Logger logger = LoggerFactory.getLogger(ItemService.class);

    List<FoodItem> list = new ArrayList<>();


    @Autowired
    private FoodItemRepositoryImpl foodItemRepositoryImpl;

    @Autowired
    private FoodItemsRepository foodItemsRepository;

    private List<FoodItem> getFoodItems(){
        FoodItem foodItem = new FoodItem("Noodles",50.0,true);
        list.add(foodItem);
        return  list;
    }
//    public List<String> getAllItems()
//    {
//        return Arrays.asList("Noodles","Soup","Rice");
//    }

    public List<FoodItem> getAllItems() {

//        logger.info("calling get All Items query");
//        List<FoodItem> foodItems =  foodItemsRepository.findAll();
////        if(foodItems.isEmpty())
////        {
//            throw new ArrayIndexOutOfBoundsException("message");
////        }
//        return foodItems;
    return null;
    }


    public FoodItem saveFoodItem(FoodItem foodItem) {
        logger.info("saving food item {}",foodItem);

//        return foodItemsRepository.save(foodItem);
        return null;
    }

    public FoodItem findByItemName(String foodItem) {

        logger.info("calling get food item by name : {}",foodItem);

        return null;//foodItemsRepository.findByItemName(foodItem);
    }

    public List<FoodItem> findByAvailablle(Boolean foodItem) {
        logger.info("calling get all food items by avialbility : {}",foodItem);

        return null;//foodItemsRepository.findByAvailable(foodItem);
    }

    public List<FoodItem> findByFoodItemName(String itemName) {
        logger.info("calling get All Items by Name : {}",itemName);


        return null;// foodItemRepositoryImpl.getFoodItemByItemName(itemName);
    }

    public FoodItem updateFoodItem(FoodItem foodItem) {
        logger.info("updating food item : {}", foodItem);

        return null;// foodItemsRepository.save(foodItem);

    }

    public void deleteFoodItem(FoodItem foodItem) {
        logger.info("deleting food item : {}",foodItem);

        //foodItemsRepository.delete(foodItem);
    }
}
