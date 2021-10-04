package com.myapp.controller;


import com.myapp.dto.FoodItem;
import com.myapp.dto.MenuItem;
import com.myapp.exception.FoodItemException;
import com.myapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class V1Controller {

    @Autowired
    private ItemService itemService;


    @GetMapping("/getMenu")
    public List<MenuItem> getAllMenuItems() {
        return itemService.getAllMenuItems();
    }

    @GetMapping("/getMenuCount")
    public long getAllMenuCount() {
        return itemService.getMenuCount();
    }

    @GetMapping("/getFoodItemSorting/{sortType}")
    public List<FoodItem> getAllItemsBySorting(@PathVariable Integer sortType) {
        return itemService.getAllItemsBySorting(sortType);
    }

    @GetMapping("/getFoodItemPageable/{page}")
    public Page<FoodItem> getAllItemsByPaging(@PathVariable Integer page) {
        return itemService.getAllItemsByPaging(page);
    }

    @GetMapping("/getMenuById/{id}")
    public MenuItem getMenuItemById(@PathVariable Integer id) {
        return itemService.getMenuItemById(id);
    }

    @GetMapping("/getMenuByCategory/{category}")
    public MenuItem getAllMenuItems(@PathVariable String category) {
        return itemService.getMenuItemByCategory(category);
    }

    @PostMapping("/saveMenu")
    public MenuItem saveMenuItem(@RequestBody MenuItem menuItem) {
        return itemService.saveMenuItem(menuItem);
    }

    @GetMapping("/getItems")
    public List<FoodItem> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/save")
    public String saveItem(@RequestBody FoodItem foodItem) {
        FoodItem itemId = itemService.saveFoodItem(foodItem);

        return itemId.getItemId() != null ? "Success creating item with id: " + itemId.getItemId() : "Failure";
    }


    @PutMapping("/update")
    public String updateItem(@RequestBody FoodItem foodItem) {
        FoodItem itemId = itemService.updateFoodItem(foodItem);
        return itemId.getItemId() != null ? "Success updating item with id: " + itemId.getItemId() : "Failure";
    }

    @DeleteMapping("/delete")
    public void deleteFoodItem(@RequestBody FoodItem foodItem) {
        try {
            itemService.deleteFoodItem(foodItem);
        } catch (Exception ex) {
            throw new FoodItemException(" Unable to delete Food Item");
        }
    }

    @GetMapping("/findByAvailable")
    public List<FoodItem> findByAvailable(@RequestParam("available") Boolean itemName) {
        return itemService.findByAvailablle(itemName);
    }

    @GetMapping("/findByItemName")
    public FoodItem findByItemName(@RequestParam("itemName") String itemName) {
        FoodItem itemId = itemService.findByItemName(itemName);
        return itemId;
    }

    @GetMapping("/findFoodItemByName")
    public List<FoodItem> findFoodItemByName(@RequestParam("itemName") String itemName) {
        return itemService.findByFoodItemName(itemName);
    }

}
