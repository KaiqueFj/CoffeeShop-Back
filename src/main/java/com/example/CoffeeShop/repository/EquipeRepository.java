package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CoffeeShop.model.Equipe.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

}
