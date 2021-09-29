package com.myapp.service;

import com.myapp.dto.FoodItem;
import com.myapp.exception.FoodItemException;
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


    private List<FoodItem> getFoodItems() {

        logger.info("Executing getFoodItems ");
        FoodItem foodItem = new FoodItem("Noodles", 50.0, true);

        logger.debug("Food items available : {}", foodItem);
        list.add(foodItem);
        return list;
    }
//    public List<String> getAllItems()
//    {
//        return Arrays.asList("Noodles","Soup","Rice");
//    }

    public List<FoodItem> getAllItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            logger.info("calling get All Items query");
            foodItems = foodItemsRepository.findAll();
            if (foodItems.isEmpty()) {
                throw new FoodItemException("No Food Items found");
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            logger.warn("Exeption caused during getting all items available :  ", e.getCause());
            throw new ArrayIndexOutOfBoundsException("message");
        }
        return foodItems;
    }


    public FoodItem saveFoodItem(FoodItem foodItem) {
        logger.info("saving food item {}", foodItem);

        return foodItemsRepository.save(foodItem);
    }

    public FoodItem findByItemName(String foodItem) {

        logger.info("calling get food item by name : {}", foodItem);

        return foodItemsRepository.findByItemName(foodItem);
    }

    public List<FoodItem> findByAvailablle(Boolean foodItem) {
        logger.info("calling get all food items by avialbility : {}", foodItem);

        return foodItemsRepository.findByAvailable(foodItem);
    }

    public List<FoodItem> findByFoodItemName(String itemName) {
        logger.info("calling get All Items by Name : {}", itemName);


        return foodItemRepositoryImpl.getFoodItemByItemName(itemName);
    }

    public FoodItem updateFoodItem(FoodItem foodItem) {
        logger.info("updating food item : {}", foodItem);

        return foodItemsRepository.save(foodItem);

    }

    public void deleteFoodItem(FoodItem foodItem) {
        logger.info("deleting food item : {}", foodItem);

        foodItemsRepository.delete(foodItem);
    }
}
