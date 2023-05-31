package com.example.CoffeeShop.model.notaFiscal;

public record NotaFiscalResponseDTO(Integer id_notaFiscal, Integer nr_notaFiscal) {
  public NotaFiscalResponseDTO(NotaFiscal notaFiscal) {
    this(notaFiscal.getId_notaFiscal(), notaFiscal.getNr_notaFiscal());
  }
}
