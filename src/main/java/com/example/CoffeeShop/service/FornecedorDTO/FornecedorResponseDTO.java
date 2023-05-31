package com.example.CoffeeShop.service.FornecedorDTO;

import com.example.CoffeeShop.model.Fornecedor.Fornecedor;

public record FornecedorResponseDTO(Integer id_fornecedor, String nm_fornecedor, String nm_email) {
  public FornecedorResponseDTO(Fornecedor fornecedor) {
    this(fornecedor.getId_fornecedor(), fornecedor.getNm_fornecedor(), fornecedor.getNm_email());
  }
}
