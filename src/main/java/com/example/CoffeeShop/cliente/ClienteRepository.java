package com.example.CoffeeShop.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
