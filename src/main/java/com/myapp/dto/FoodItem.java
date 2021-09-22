package com.myapp.dto;

import org.springframework.data.annotation.Id;


public class FoodItem {


    @Id
    private Integer itemId;
    private String itemName;
    private double itemCost;
    private boolean available;

    public FoodItem(String itemName, double itemCost, boolean available) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.available = available;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemCost=" + itemCost +
                ", available=" + available +
                '}';
    }
}
