package com.example.CoffeeShop.service.ProdutoDTO;

import com.example.CoffeeShop.model.Estoque.Estoque;
import com.example.CoffeeShop.model.pedido.Pedido;

public record ProdutoRequestDTO(String nm_produto, String vl_produto, Integer qt_produto, Pedido T_pedido_id_pedido,
    Estoque T_estoque_id_estoque) {

}