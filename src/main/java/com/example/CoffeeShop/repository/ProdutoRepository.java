package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
