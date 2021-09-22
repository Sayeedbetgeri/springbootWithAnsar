package com.myapp.repository;

import com.myapp.dto.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class FoodItemRepositoryImpl {


//    @Autowired
//    private MongoOperations mongoOperations;

    public List<FoodItem> getFoodItemByItemName(String foodName)
    {
//        Criteria criteria = Criteria.where("itemName").is(foodName);
//        return  mongoOperations.find(Query.query(criteria), FoodItem.class);
 return null;
    }

}
