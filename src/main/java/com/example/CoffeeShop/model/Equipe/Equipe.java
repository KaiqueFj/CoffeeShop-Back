package com.example.CoffeeShop.model.Equipe;

import java.util.ArrayList;
import java.util.List;

import com.example.CoffeeShop.model.funcionario.Funcionario;
import com.example.CoffeeShop.service.EquipeDTO.EquipeRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "T_equipe")
@Entity(name = "T_equipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_equipe")

public class Equipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_equipe;
  private String ds_descricao;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "T_equipe_id_equipe")
  @JsonBackReference
  private List<Funcionario> funcionarios;

  public Equipe(EquipeRequestDTO data) {
    this.ds_descricao = data.ds_descricao();
    this.funcionarios = data.funcionarios();
  }

}
