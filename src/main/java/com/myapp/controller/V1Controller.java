package com.myapp.controller;


import com.myapp.dto.FoodItem;
import com.myapp.dto.MenuItem;
import com.myapp.exception.ExceptionDTO;
import com.myapp.exception.FoodItemException;
import com.myapp.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public MenuItem getAllMenuItems(
            @ApiParam(name = "category", value = "category", defaultValue = "Starters")
            @PathVariable("category") String category) {
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
    @ApiOperation(value = "This Api will delete the Food item from FoodItem repository",notes = "Delete Food Item")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionDTO.class),
            @ApiResponse(code = 404, message = "Item Not Found", response = ExceptionDTO.class)
    })
    public void deleteFoodItem(
            @ApiParam(name = "Request Body for FoodItem", value = "FoodItem Object", example = "{itemName:Noodles}")
            @RequestBody FoodItem foodItem) {
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
