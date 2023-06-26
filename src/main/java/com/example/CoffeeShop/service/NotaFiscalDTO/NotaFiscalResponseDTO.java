package com.example.CoffeeShop.service.NotaFiscalDTO;

import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;
import com.example.CoffeeShop.model.pedido.Pedido;

public record NotaFiscalResponseDTO(Integer id_Notafiscal, Integer nr_notafiscal, Pedido pedidos) {
  public NotaFiscalResponseDTO(NotaFiscal notaFiscal) {
    this(notaFiscal.getId_Notafiscal(), notaFiscal.getNr_notafiscal(), notaFiscal.getPedidos());
  }
}
