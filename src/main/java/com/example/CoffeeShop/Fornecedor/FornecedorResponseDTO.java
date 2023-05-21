package com.example.CoffeeShop.Fornecedor;

import com.example.CoffeeShop.model.Fornecedor;

public record FornecedorResponseDTO(Integer id_fornecedor, String nm_fornecedor, String nm_email) {
  public FornecedorResponseDTO(Fornecedor fornecedor) {
    this(fornecedor.getId_fornecedor(), fornecedor.getNm_fornecedor(), fornecedor.getNm_email());
  }
}
