package com.example.CoffeeShop.service.EquipeDTO;

import java.util.List;

import com.example.CoffeeShop.model.Equipe.Equipe;
import com.example.CoffeeShop.model.funcionario.Funcionario;

public record EquipeResponseDTO(Integer id_equipe, String ds_descricao, List<Funcionario> funcionarios) {
  public EquipeResponseDTO(Equipe equipe) {
    this(equipe.getId_equipe(), equipe.getDs_descricao(), equipe.getFuncionarios());
  }
}
