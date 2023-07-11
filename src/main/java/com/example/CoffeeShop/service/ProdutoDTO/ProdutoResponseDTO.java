package com.example.CoffeeShop.service.ProdutoDTO;

import com.example.CoffeeShop.model.Estoque.Estoque;
import com.example.CoffeeShop.model.pedido.Pedido;
import com.example.CoffeeShop.model.produto.Produto;

public record ProdutoResponseDTO(Integer id_produto, String nm_produto, String vl_produto, Integer qt_produto,
    Pedido T_pedido_id_pedido, Estoque T_estoque_id_estoque) {
  public ProdutoResponseDTO(Produto produto) {
    this(produto.getId_produto(), produto.getNm_produto(), produto.getVl_produto(), produto.getQt_produto(),
        produto.getT_pedido_id_pedido(), produto.getT_estoque_id_estoque());
  }
}
