package com.example.CoffeeShop.service.NotaFiscalDTO;

import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;

public record NotaFiscalResponseDTO(Integer id_notaFiscal, Integer nr_notaFiscal) {
  public NotaFiscalResponseDTO(NotaFiscal notaFiscal) {
    this(notaFiscal.getId_notaFiscal(), notaFiscal.getNr_notaFiscal());
  }
}
