package com.example.CoffeeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShop.funcionario.Funcionario;
import com.example.CoffeeShop.funcionario.FuncionarioReponseDTO;
import com.example.CoffeeShop.funcionario.FuncionarioRepository;
import com.example.CoffeeShop.funcionario.FuncionarioRequestDTO;

import java.util.List;


@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

  @Autowired
  private FuncionarioRepository repository;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping
  public void saveFuncionario(@RequestBody FuncionarioRequestDTO data) {
    Funcionario funcionarioData = new Funcionario(data);
    repository.save(funcionarioData);
    return;

  }
  
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping
  public List<FuncionarioReponseDTO> getAll() {
    
    List<FuncionarioReponseDTO> funcionarioList = repository.findAll().stream().map(FuncionarioReponseDTO::new).toList();
    
    return funcionarioList;
  }
}
