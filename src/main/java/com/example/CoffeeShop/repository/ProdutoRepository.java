package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CoffeeShop.model.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
