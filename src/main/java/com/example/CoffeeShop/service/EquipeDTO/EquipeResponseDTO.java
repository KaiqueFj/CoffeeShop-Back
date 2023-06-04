package com.example.CoffeeShop.service.EquipeDTO;

import java.util.List;

import com.example.CoffeeShop.model.Equipe.Equipe;
import com.example.CoffeeShop.model.funcionario.Funcionario;

public record EquipeResponseDTO(Integer id_equipe, String ds_descricao) {
  public EquipeResponseDTO(Equipe equipe) {
    this(equipe.getId_equipe(), equipe.getDs_descricao());
  }
}
