package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
