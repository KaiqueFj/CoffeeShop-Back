package com.example.CoffeeShop.model.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;

@Repository
public interface PedidoRepository extends JpaRepository<NotaFiscal, Integer> {

}
