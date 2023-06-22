package com.example.CoffeeShop.service.clienteDTO;

import java.util.List;

import com.example.CoffeeShop.model.cliente.Cliente;
import com.example.CoffeeShop.model.pedido.Pedido;

public record ClienteResponseDTO(Integer id_cliente, String nm_cliente, List<Pedido> pedidos) {
  public ClienteResponseDTO(Cliente cliente) {
    this(cliente.getId_cliente(), cliente.getNm_cliente(), cliente.getPedidos());
  }
}
