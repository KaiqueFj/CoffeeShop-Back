package com.example.CoffeeShop.service.FuncionarioDTO;

import com.example.CoffeeShop.model.Equipe.Equipe;

public record FuncionarioRequestDTO(String nm_nome, String dt_admissao, String vl_salario, String ds_funcao,
        Equipe T_equipe_id_equipe) {

}