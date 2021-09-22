package com.myapp.controller;


import com.myapp.dto.FoodItem;
import com.myapp.exception.FoodItemException;
import com.myapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class V1Controller {

    @Autowired
    private ItemService itemService;

//    @GetMapping("/getItems")
//    public String getItems() {
//        return "hello";
//    }

    @GetMapping("/getItems")
    public List<FoodItem> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/save")
    public String saveItem(@RequestBody  FoodItem foodItem)
    {
        FoodItem itemId = itemService.saveFoodItem(foodItem);

        return  itemId.getItemId() !=null? "Success creating item with id: " + itemId.getItemId() : "Failure" ;
    }


    @PutMapping("/update")
    public String updateItem(@RequestBody  FoodItem foodItem)
    {
        FoodItem itemId = itemService.updateFoodItem(foodItem);
        return  itemId.getItemId() !=null? "Success updating item with id: " + itemId.getItemId() : "Failure" ;
    }

    @DeleteMapping("/delete")
    public void deleteFoodItem(@RequestBody  FoodItem foodItem) {
        try {
            itemService.deleteFoodItem(foodItem);
        }catch (Exception ex)
        {
            throw new FoodItemException("Unable to delete Food Item");
        }
    }

    @GetMapping("/findByAvailable")
    public List<FoodItem> findByAvailable(@RequestParam("available")  Boolean itemName)
    {
        return itemService.findByAvailablle(itemName);
    }

    @GetMapping("/findByItemName")
    public FoodItem findByItemName(@RequestParam("itemName")  String itemName)
    {
        FoodItem itemId = itemService.findByItemName(itemName);
        return  itemId;
    }

    @GetMapping("/findFoodItemByName")
    public List<FoodItem> findFoodItemByName(@RequestParam("itemName")  String itemName)
    {
        return itemService.findByFoodItemName(itemName);
    }

}
