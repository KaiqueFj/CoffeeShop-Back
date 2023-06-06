package com.example.CoffeeShop.model.funcionario;

import com.example.CoffeeShop.model.Equipe.Equipe;
import com.example.CoffeeShop.service.FuncionarioDTO.FuncionarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "T_funcionario")
@Entity(name = "T_funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_funcionario")

public class Funcionario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_funcionario;
  private String nm_nome;
  private String dt_admissao;
  private String vl_salario;
  private String ds_funcao;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "T_equipe_id_equipe", referencedColumnName = "id_equipe")
  private Equipe T_equipe_id_equipe;

  public Funcionario(FuncionarioRequestDTO data) {
    this.nm_nome = data.nm_nome();
    this.dt_admissao = data.dt_admissao();
    this.vl_salario = data.vl_salario();
    this.ds_funcao = data.ds_funcao();
    this.T_equipe_id_equipe = data.T_equipe_id_equipe();
  }

}
