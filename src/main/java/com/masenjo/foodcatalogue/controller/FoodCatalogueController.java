package com.masenjo.foodcatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masenjo.foodcatalogue.dto.FoodCataloguePage;
import com.masenjo.foodcatalogue.dto.FoodItemDto;
import com.masenjo.foodcatalogue.service.FoodCatalogueService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody FoodItemDto foodItemDto) {
        FoodItemDto savedFoodItem = foodCatalogueService.addFoodItem(foodItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFoodItem);
    }

	@GetMapping("/fetchFoodCatalogueById/{restaurantId}")
	public ResponseEntity<FoodCataloguePage> fetchFoodCatalogueDetails(@PathVariable Integer restaurantId) {
		FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePage(restaurantId);
		return ResponseEntity.ok(foodCataloguePage);
	}
	
}
