package com.example.CoffeeShop.service.PedidoDTO;

import com.example.CoffeeShop.model.pedido.Pedido;

public record PedidoResponseDTO(Integer id_Pedido, String ds_pedido, String dt_pedido, String vl_pedido) {
  public PedidoResponseDTO(Pedido pedido) {
    this(pedido.getId_Pedido(), pedido.getDs_pedido(), pedido.getDt_pedido(), pedido.getVl_pedido());
  }
}
