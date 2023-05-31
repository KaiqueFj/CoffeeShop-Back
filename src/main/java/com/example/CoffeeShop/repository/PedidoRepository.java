package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CoffeeShop.model.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
