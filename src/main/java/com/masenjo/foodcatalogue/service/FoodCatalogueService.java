package com.masenjo.foodcatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masenjo.foodcatalogue.dto.FoodCataloguePage;
import com.masenjo.foodcatalogue.dto.FoodItemDto;
import com.masenjo.foodcatalogue.dto.Restaurant;
import com.masenjo.foodcatalogue.entity.FoodItem;
import com.masenjo.foodcatalogue.mapper.FoodItemMapper;
import com.masenjo.foodcatalogue.repo.FoodItemRepo;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

	@Autowired
	RestTemplate restTemplate;

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
        FoodItem savedFoodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(savedFoodItem);
    }

	public FoodCataloguePage fetchFoodCataloguePage(Integer restaurantId) {
		List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
		Restaurant restaurant = fetchRestaurantDetailsFromMS(restaurantId);
		return createFoodCataloguePage(foodItemList, restaurant);
	}

	private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
		return foodItemRepo.findByRestaurantId(restaurantId);
	}

	private Restaurant fetchRestaurantDetailsFromMS(Integer restaurantId) {
		return restTemplate.getForObject("http://RESTAURANTLISTING/restaurant/fetchById/"+restaurantId, Restaurant.class);
	}

	private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
		FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
		foodCataloguePage.setFoodItemsList(foodItemList);
		foodCataloguePage.setRestaurant(restaurant);
		return foodCataloguePage;
	}

}
