package com.example.CoffeeShop.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
