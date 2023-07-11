package com.example.CoffeeShop.service.EstoqueDTO;

import java.util.List;

import com.example.CoffeeShop.model.produto.Produto;

public record EstoqueRequestDTO(Integer nr_quantidade_min, Integer nr_quantidade_max,
        Integer nr_quantidade_atual_items, List<Produto> produtos) {

}
