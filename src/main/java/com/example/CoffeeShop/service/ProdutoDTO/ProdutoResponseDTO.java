package com.example.CoffeeShop.service.ProdutoDTO;

import com.example.CoffeeShop.model.produto.Produto;

public record ProdutoResponseDTO(Integer id_produto, String nm_produto, String vl_produto, Integer qt_produto) {
  public ProdutoResponseDTO(Produto produto) {
    this(produto.getId_produto(), produto.getNm_produto(), produto.getVl_produto(), produto.getQt_produto());
  }
}
