package com.myapp.service;

import com.mongodb.MongoException;
import com.myapp.dto.FoodItem;
import com.myapp.dto.MenuItem;
import com.myapp.exception.FoodItemException;
import com.myapp.exception.MenuItemException;
import com.myapp.repository.FoodItemRepositoryImpl;
import com.myapp.repository.FoodItemsRepository;
import com.myapp.repository.MenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private MenuItemRepository menuItemRepository;


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

    /*
    Sorting items
    Low to high Cost
    High to low Cost
    Alphabetical names
    reverse alphabetical names
    available first
    filter on non available
     */


    public List<FoodItem> getAllItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            logger.info("calling get All Items query");
            Sort sorting = Sort.by(Sort.Direction.ASC,"itemName");
            foodItems = foodItemsRepository.findAll(sorting);
            if (foodItems.isEmpty()) {
                throw new FoodItemException("No Food Items found");
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            logger.warn("Exeption caused during getting all items available :  ", e.getCause());
            throw new ArrayIndexOutOfBoundsException("message");
        }
        return foodItems;
    }


    public List<FoodItem> getAllItemsBySorting(Integer sortType) {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            logger.info("calling get All Items query");
            Sort sorting = Sort.by(Sort.Direction.ASC,"itemName");
            if(sortType.equals(1))
            {
             sorting= Sort.by(Sort.Direction.ASC,"itemCost");
            }else if(sortType.equals(2))
            {
                sorting= Sort.by(Sort.Direction.DESC,"itemCost");
            }
            else if(sortType.equals(4))
            {
                sorting= Sort.by(Sort.Direction.DESC,"itemName");
            }

            foodItems = foodItemsRepository.findAll(sorting);
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

    public List<MenuItem> getAllMenuItems() {

        List<MenuItem> menuItems = new ArrayList<>();
        logger.info("Getting all menu items");
        try {
            menuItems =  menuItemRepository.findAll();
            if(menuItems.isEmpty())
            {
                throw new MenuItemException("No Menu Items Found");
            }
        }catch (MongoException ex)
        {
            throw new MongoException("Exception while fetching Menu Items");
        }
        return menuItems;
    }

    public MenuItem saveMenuItem(MenuItem menuItem) {
        try {
            logger.info("menuItem : {}", menuItem );
            if(!menuItemRepository.existsById(menuItem.getCategoryId())) {
                return menuItemRepository.save(menuItem);
            }else
            {
                throw new MenuItemException("Id already Existis");
            }
        }catch (MongoException e)
        {
            logger.error(e.getMessage(),e.getCause());
            throw new MongoException("Exception while saving ",e);
        }
    }

    public MenuItem getMenuItemById(Integer id) {
        try
        {
           return menuItemRepository.findById(id).get();
        }catch (MongoException e)
        {
            throw new MongoException("Exception while fetching id" + id ,e);
        }
    }

    public MenuItem getMenuItemByCategory(String category) {
        return null;//menuItemRepository.findById(category).get();
    }

    public long getMenuCount() {
        return menuItemRepository.count();
    }

    public Page<FoodItem> getAllItemsByPaging(Integer page) {
        try {
            logger.info("calling get All Items query");

            Pageable pageable = PageRequest.of(page,2);
            return foodItemsRepository.findAll(pageable);
//            if (foodItems.isEmpty()) {
//                throw new FoodItemException("No Food Items found");
//            }
        } catch (ArrayIndexOutOfBoundsException e) {

            logger.warn("Exeption caused during getting all items available :  ", e.getCause());
            throw new ArrayIndexOutOfBoundsException("message");
        }
    //    return foodItems;

    }
}
