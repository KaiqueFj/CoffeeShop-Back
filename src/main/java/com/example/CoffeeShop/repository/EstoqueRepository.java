package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.Estoque.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

}
