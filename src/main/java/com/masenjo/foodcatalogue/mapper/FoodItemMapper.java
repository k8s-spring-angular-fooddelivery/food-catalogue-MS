package com.masenjo.foodcatalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.masenjo.foodcatalogue.dto.FoodItemDto;
import com.masenjo.foodcatalogue.entity.FoodItem;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDtoToFoodItem(FoodItemDto foodItemDto);
    FoodItemDto mapFoodItemToFoodItemDto(FoodItem foodItem);
}
