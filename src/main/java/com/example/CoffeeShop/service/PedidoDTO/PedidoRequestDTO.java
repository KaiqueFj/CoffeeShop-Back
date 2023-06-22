package com.example.CoffeeShop.service.PedidoDTO;

import com.example.CoffeeShop.model.cliente.Cliente;

public record PedidoRequestDTO(String ds_pedido, String dt_pedido, String vl_pedido, Cliente T_cliente_id_cliente) {

}