package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CoffeeShop.model.funcionario.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
