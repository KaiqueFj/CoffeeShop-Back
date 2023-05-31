package com.example.CoffeeShop.service.EquipeDTO;

import com.example.CoffeeShop.model.Equipe.Equipe;

public record EquipeResponseDTO(Integer id_equipe, String ds_descricao) {
  public EquipeResponseDTO(Equipe equipe) {
    this(equipe.getId_equipe(), equipe.getDs_descricao());
  }
}
