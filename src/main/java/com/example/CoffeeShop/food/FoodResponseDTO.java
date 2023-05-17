package com.example.CoffeeShop.food;

public record FoodResponseDTO(Integer id, String title, String image, Integer price) {
    public FoodResponseDTO (Food food){
      this(food.getId(), food.getTitle(), food.getImage(), food.getPrice());
    }
}
