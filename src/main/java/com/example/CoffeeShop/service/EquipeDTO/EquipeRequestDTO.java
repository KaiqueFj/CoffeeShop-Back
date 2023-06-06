package com.example.CoffeeShop.service.EquipeDTO;

import java.util.List;

import com.example.CoffeeShop.model.funcionario.Funcionario;

public record EquipeRequestDTO(String ds_descricao, List<Funcionario> funcionarios) {

}