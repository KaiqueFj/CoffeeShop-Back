package com.example.CoffeeShop.service.NotaFiscalDTO;

import com.example.CoffeeShop.model.pedido.Pedido;

public record NotaFiscalRequestDTO(Integer nr_notafiscal, Pedido pedidos) {

}