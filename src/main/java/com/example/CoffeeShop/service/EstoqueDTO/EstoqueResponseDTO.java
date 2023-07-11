package com.example.CoffeeShop.service.EstoqueDTO;

import com.example.CoffeeShop.model.Estoque.Estoque;

public record EstoqueResponseDTO(Integer id_estoque, Integer nr_quantidade_min, Integer nr_quantidade_max,
    Integer nr_quantidade_atual_items) {
  public EstoqueResponseDTO(Estoque estoque) {
    this(estoque.getId_estoque(), estoque.getNr_quantidade_min(), estoque.getNr_quantidade_max(),
        estoque.getNr_quantidade_atual_items());
  }

}
