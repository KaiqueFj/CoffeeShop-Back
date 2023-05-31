package com.example.CoffeeShop.model.Equipe;

import java.util.Set;

import org.hibernate.mapping.List;

import com.example.CoffeeShop.model.funcionario.Funcionario;
import com.example.CoffeeShop.service.EquipeDTO.EquipeRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

  @OneToMany(mappedBy = "equipeFK")
  private Set<Funcionario> funcionario;

  public Equipe(EquipeRequestDTO data) {
    this.ds_descricao = data.ds_descricao();
  }

}
