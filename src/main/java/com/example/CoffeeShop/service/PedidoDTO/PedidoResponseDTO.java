package com.example.CoffeeShop.service.PedidoDTO;

import java.util.List;

import com.example.CoffeeShop.model.cliente.Cliente;
import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;
import com.example.CoffeeShop.model.pedido.Pedido;
import com.example.CoffeeShop.model.produto.Produto;

public record PedidoResponseDTO(Integer id_Pedido, String ds_pedido, String dt_pedido, String vl_pedido,
    Cliente T_cliente_id_cliente, NotaFiscal T_notafiscal_id_notafiscal, List<Produto> produtos) {
  public PedidoResponseDTO(Pedido pedido) {
    this(pedido.getId_Pedido(), pedido.getDs_pedido(), pedido.getDt_pedido(), pedido.getVl_pedido(),
        pedido.getT_cliente_id_cliente(), pedido.getT_notafiscal_id_notafiscal(), pedido.getProdutos());
  }
}
