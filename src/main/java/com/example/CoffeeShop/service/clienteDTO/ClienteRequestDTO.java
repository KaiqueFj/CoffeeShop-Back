package com.example.CoffeeShop.service.clienteDTO;

import java.util.List;

import com.example.CoffeeShop.model.pedido.Pedido;

public record ClienteRequestDTO(String nm_cliente, List<Pedido> pedidos) {

}