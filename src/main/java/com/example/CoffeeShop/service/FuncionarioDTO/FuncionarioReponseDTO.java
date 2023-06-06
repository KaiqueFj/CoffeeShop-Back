package com.example.CoffeeShop.service.FuncionarioDTO;

import com.example.CoffeeShop.model.Equipe.Equipe;
import com.example.CoffeeShop.model.funcionario.Funcionario;

public record FuncionarioReponseDTO(Integer id_funcionario, String nm_nome, String dt_admissao, String vl_salario,
    String ds_funcao, Equipe T_equipe_id_equipe) {
  public FuncionarioReponseDTO(Funcionario funcionario) {
    this(funcionario.getId_funcionario(), funcionario.getNm_nome(), funcionario.getDt_admissao(),
        funcionario.getVl_salario(),
        funcionario.getDs_funcao(), funcionario.getT_equipe_id_equipe());
  }
}
