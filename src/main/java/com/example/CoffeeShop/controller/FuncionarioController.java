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

import com.example.CoffeeShop.model.cliente.Cliente;
import com.example.CoffeeShop.model.funcionario.Funcionario;
import com.example.CoffeeShop.repository.FuncionarioRepository;
import com.example.CoffeeShop.service.Exception.CustomException;
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
  public ResponseEntity<?> saveFuncionario(@RequestBody FuncionarioRequestDTO data) {
    try {
      Funcionario funcionarioData = new Funcionario(data);
      repository.save(funcionarioData);
      return new ResponseEntity<Funcionario>(funcionarioData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Get all employer
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getAllFuncionarios")
  public ResponseEntity<?> getAll() {
    try {

      List<FuncionarioReponseDTO> funcionarioList = repository.findAll().stream().map(FuncionarioReponseDTO::new)
          .toList();

      return new ResponseEntity<List<FuncionarioReponseDTO>>(funcionarioList, HttpStatus.FOUND);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

  }

  // Get the employer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getEmployeebyId/{id_funcionario}")
  public ResponseEntity<?> getBookById(@PathVariable Integer id_funcionario) {
    try {
      Optional<Funcionario> funcionarioData = repository.findById(id_funcionario);
      if (!funcionarioData.isPresent()) {
        String errorMessage = "Não foi possível encontrar o cliente. Tente novamente !";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

      }
      return ResponseEntity.status(HttpStatus.FOUND).body(funcionarioData);

    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Delete the employer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteFuncionarioByid/{id_funcionario}")
  private ResponseEntity<?> deleteFornecedor(@PathVariable Integer id_funcionario) {
    try {
      boolean deleted = repository.existsById(id_funcionario);

      if (deleted) {
        repository.deleteById(id_funcionario);
        return ResponseEntity.ok("Funcionario deletado com sucesso!");
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Erro ao tentar deletar funcionario, tente novamente");
      }
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Update the employe data
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateFuncionario/{id_funcionario}")
  private ResponseEntity<?> updateFuncionariobyId(@PathVariable Integer id_funcionario,
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
        return ResponseEntity.status(HttpStatus.OK).body(updateFuncionarioData);
      } else {
        String errorMessage = "Não foi possível atualizar os dados do cliente. Tente novamente !";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Cliente>(HttpStatus.NOT_MODIFIED);
    }
  }
}
