package com.example.CoffeeShop.funcionario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "T_funcionario")
@Entity(name = "T_funcionario")
@Getter
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

  public Funcionario(FuncionarioRequestDTO data) {
    this.ds_funcao = data.ds_funcao();
    this.vl_salario = data.vl_salario();
    this.dt_admissao = data.dt_admissao();
    this.nm_nome = data.nm_nome();
  }

}
