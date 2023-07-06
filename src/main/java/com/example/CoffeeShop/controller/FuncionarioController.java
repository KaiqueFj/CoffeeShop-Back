package com.example.CoffeeShop.controller;

import org.apache.coyote.Response;
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
import com.example.CoffeeShop.repository.FuncionarioRepository;
import com.example.CoffeeShop.service.FuncionarioDTO.FuncionarioReponseDTO;
import com.example.CoffeeShop.service.FuncionarioDTO.FuncionarioRequestDTO;

import java.util.Optional;
import java.util.List;

@RequestMapping("funcionario")

@RestController
public class FuncionarioController {

  @Autowired
  private FuncionarioRepository repository;

  // Add the employe in database
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping(path = "/addFuncionario", consumes = "application/json;charset=UTF-8", headers = "content-type=text/json")
  public ResponseEntity<Funcionario> saveFuncionario(@RequestBody FuncionarioRequestDTO data) {
    try {
      Funcionario funcionarioData = new Funcionario(data);
      repository.save(funcionarioData);
      return new ResponseEntity<Funcionario>(funcionarioData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      return new ResponseEntity<Funcionario>(HttpStatus.BAD_REQUEST);
    }
  }

  // Get all employer
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getAllFuncionarios")
  public ResponseEntity<List<FuncionarioReponseDTO>> getAll() {
    try {

      List<FuncionarioReponseDTO> funcionarioList = repository.findAll().stream().map(FuncionarioReponseDTO::new)
          .toList();

      return new ResponseEntity<List<FuncionarioReponseDTO>>(funcionarioList, HttpStatus.FOUND);
    }

    catch (Exception e) {
      return new ResponseEntity<List<FuncionarioReponseDTO>>(HttpStatus.NOT_FOUND);
    }

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
  private ResponseEntity<String> deleteFornecedor(@PathVariable Integer id_funcionario) {
    try {
      boolean deleted = repository.existsById(id_funcionario);

      if (deleted) {
        repository.deleteById(id_funcionario);
        return ResponseEntity.ok("Resouce Deleted successfully");
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong, try again");
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong, try again");
    }
  }

  // Update the employe data
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateFuncionario/{id_funcionario}")
  private ResponseEntity<Funcionario> updateFuncionariobyId(@PathVariable Integer id_funcionario,
      @RequestBody Funcionario newFuncionarioData) {

    try {
      Optional<Funcionario> oldFuncionarioData = repository.findById(id_funcionario);

      if (oldFuncionarioData.isPresent()) {

        Funcionario updateFuncionarioData = oldFuncionarioData.get();
        updateFuncionarioData.setNm_nome(newFuncionarioData.getNm_nome());
        updateFuncionarioData.setDt_admissao(newFuncionarioData.getDt_admissao());
        updateFuncionarioData.setVl_salario(newFuncionarioData.getVl_salario());
        updateFuncionarioData.setDs_funcao(newFuncionarioData.getDs_funcao());
        updateFuncionarioData.setT_equipe_id_equipe(newFuncionarioData.getT_equipe_id_equipe());

        repository.save(updateFuncionarioData);
        return new ResponseEntity<Funcionario>(updateFuncionarioData, HttpStatus.OK);
      } else {
        return new ResponseEntity<Funcionario>(HttpStatus.BAD_REQUEST);
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Funcionario>(HttpStatus.BAD_REQUEST);
    }
  }
}
