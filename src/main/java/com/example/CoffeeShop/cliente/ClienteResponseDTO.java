package com.example.CoffeeShop.cliente;

import com.example.CoffeeShop.model.Cliente;

public record ClienteResponseDTO(Integer id_cliente, String nm_cliente) {
  public ClienteResponseDTO(Cliente cliente) {
    this(cliente.getId_cliente(), cliente.getNm_cliente());
  }
}
