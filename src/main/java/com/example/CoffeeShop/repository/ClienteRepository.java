package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
