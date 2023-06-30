package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.Fornecedor.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}
