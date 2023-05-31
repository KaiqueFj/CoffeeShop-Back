package com.example.CoffeeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShop.model.funcionario.Funcionario;
import com.example.CoffeeShop.model.funcionario.FuncionarioReponseDTO;
import com.example.CoffeeShop.model.funcionario.FuncionarioRepository;
import com.example.CoffeeShop.model.funcionario.FuncionarioRequestDTO;

import java.util.Optional;
import java.util.List;

@RequestMapping("funcionario")

@RestController
public class FuncionarioController {

  @Autowired
  private FuncionarioRepository repository;

  // Add the employe in database
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/addFuncionario")
  public void saveFuncionario(@RequestBody FuncionarioRequestDTO data) {
    Funcionario funcionarioData = new Funcionario(data);
    repository.save(funcionarioData);
    return;

  }

  // Get all employer
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getAllFuncionarios")
  public List<FuncionarioReponseDTO> getAll() {

    List<FuncionarioReponseDTO> funcionarioList = repository.findAll().stream().map(FuncionarioReponseDTO::new)
        .toList();

    return funcionarioList;
  }

  // Get the employer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getEmployeebyId/{id_funcionario}")
  public ResponseEntity<Funcionario> getBookById(@PathVariable Integer id_funcionario) {
    Optional<Funcionario> funcionarioData = repository.findById(id_funcionario);
    if (funcionarioData.isPresent()) {
      return new ResponseEntity<>(funcionarioData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the employer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteFuncionarioByid/{id_funcionario}")
  private void deleteFornecedor(@PathVariable Integer id_funcionario) {
    try {
      repository.deleteById(id_funcionario);
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Update the employe data
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateFuncionario/{id_funcionario}")
  private void updateFuncionariobyId(@PathVariable Integer id_funcionario,
      @RequestBody Funcionario newFuncionarioData) {

    try {
      Optional<Funcionario> oldFuncionarioData = repository.findById(id_funcionario);

      if (oldFuncionarioData.isPresent()) {

        Funcionario updateFuncionarioData = oldFuncionarioData.get();
        updateFuncionarioData.setNm_nome(newFuncionarioData.getNm_nome());
        updateFuncionarioData.setDt_admissao(newFuncionarioData.getDt_admissao());
        updateFuncionarioData.setVl_salario(newFuncionarioData.getVl_salario());
        updateFuncionarioData.setDs_funcao(newFuncionarioData.getDs_funcao());

        repository.save(updateFuncionarioData);
        return;
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
