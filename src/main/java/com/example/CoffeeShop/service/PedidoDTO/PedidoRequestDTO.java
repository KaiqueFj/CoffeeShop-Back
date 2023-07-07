package com.example.CoffeeShop.service.PedidoDTO;

import java.util.List;

import com.example.CoffeeShop.model.cliente.Cliente;
import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;
import com.example.CoffeeShop.model.produto.Produto;

public record PedidoRequestDTO(String ds_pedido, String dt_pedido, String vl_pedido, Cliente T_cliente_id_cliente,
        NotaFiscal T_notafiscal_id_notafiscal, List<Produto> produtos) {

}