package com.example.CoffeeShop.model.pedido;

import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;

public record PedidoResponseDTO(Integer id_notaFiscal, Integer nr_notaFiscal) {
  public PedidoResponseDTO(NotaFiscal notaFiscal) {
    this(notaFiscal.getId_notaFiscal(), notaFiscal.getNr_notaFiscal());
  }
}
