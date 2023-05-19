package com.example.CoffeeShop.funcionario;

public record FuncionarioReponseDTO(Integer id_funcionario, String nm_nome, String dt_admissao, String vl_salario,
    String ds_funcao) {
  public FuncionarioReponseDTO(Funcionario funcionario) {
    this(funcionario.getId_funcionario(), funcionario.getNm_nome(), funcionario.getDt_admissao(),
        funcionario.getVl_salario(),
        funcionario.getDs_funcao());
  }
}
