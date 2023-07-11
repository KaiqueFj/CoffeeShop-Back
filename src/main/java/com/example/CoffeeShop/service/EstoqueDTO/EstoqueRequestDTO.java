package com.example.CoffeeShop.service.EstoqueDTO;

public record EstoqueRequestDTO(Integer nr_quantidade_min, Integer nr_quantidade_max,
    Integer nr_quantidade_atual_items) {

}
