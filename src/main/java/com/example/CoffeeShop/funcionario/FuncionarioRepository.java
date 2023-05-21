package com.example.CoffeeShop.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CoffeeShop.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
