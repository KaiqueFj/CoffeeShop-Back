package com.example.CoffeeShop.service.EstoqueDTO;

import java.util.List;

import com.example.CoffeeShop.model.Estoque.Estoque;
import com.example.CoffeeShop.model.produto.Produto;

public record EstoqueResponseDTO(Integer id_estoque, Integer nr_quantidade_min, Integer nr_quantidade_max,
    Integer nr_quantidade_atual_items, List<Produto> produtos) {
  public EstoqueResponseDTO(Estoque estoque) {
    this(estoque.getId_estoque(), estoque.getNr_quantidade_min(), estoque.getNr_quantidade_max(),
        estoque.getNr_quantidade_atual_items(), estoque.getProdutos());
  }

}
