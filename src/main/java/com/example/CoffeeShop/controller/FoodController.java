package com.example.CoffeeShop.controller;

import com.example.CoffeeShop.food.Food;
import com.example.CoffeeShop.food.FoodRepository;
import com.example.CoffeeShop.food.FoodRequestDTO;
import com.example.CoffeeShop.food.FoodResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("food")
public class FoodController {

  @Autowired
  private FoodRepository repository;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping
  public void saveFood(@RequestBody FoodRequestDTO data) {
    Food foodData = new Food(data);
    repository.save(foodData);
    return;

  }
  
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping
  public List<FoodResponseDTO> getAll() {
    
    List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
    
    return foodList;
  }
}
