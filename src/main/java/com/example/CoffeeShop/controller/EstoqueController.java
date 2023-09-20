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

import com.example.CoffeeShop.model.Estoque.Estoque;
import com.example.CoffeeShop.model.cliente.Cliente;
import com.example.CoffeeShop.repository.EstoqueRepository;
import com.example.CoffeeShop.service.EstoqueDTO.EstoqueRequestDTO;
import com.example.CoffeeShop.service.EstoqueDTO.EstoqueResponseDTO;
import com.example.CoffeeShop.service.Exception.CustomException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("stock")
public class EstoqueController {

  @Autowired
  private EstoqueRepository repository;

  // Post the Client
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("addstock")
  public ResponseEntity<?> saveStock(@RequestBody EstoqueRequestDTO data) {
    try {
      Estoque estoqueData = new Estoque(data);
      repository.save(estoqueData);
      return new ResponseEntity<Estoque>(estoqueData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

  }

  // Get all clients
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllStock")
  public ResponseEntity<?> getAll() {
    try {
      List<EstoqueResponseDTO> stockList = repository.findAll().stream().map(EstoqueResponseDTO::new)
          .toList();

      return new ResponseEntity<List<EstoqueResponseDTO>>(stockList, HttpStatus.FOUND);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getStock/{id_estoque}")
  public ResponseEntity<?> getBookById(@PathVariable Integer id_estoque) {
    try {
      Optional<Estoque> estoqueData = repository.findById(id_estoque);
      if (!estoqueData.isPresent()) {
        String errorMessage = "Não foi possível encontrar o cliente. Tente novamente !";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }
      return ResponseEntity.status(HttpStatus.FOUND).body(estoqueData);
    }

    catch (Exception e) {
      String errorMessage = "Não foi possível verificar a lista de usuários, tente novamente ! " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteStock/{id_estoque}")
  private ResponseEntity<?> deleteStock(@PathVariable Integer id_estoque) {
    try {
      boolean deleted = repository.existsById(id_estoque);

      if (deleted) {
        repository.deleteById(id_estoque);
        return ResponseEntity.ok("Estoque Deletado com sucesso!");
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Não foi possível encontrar o estoque para ser deletado");
      }
    }

    catch (Exception e) {
      String errorMessage = "Não foi possível verificar a lista de usuários, tente novamente ! " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateStock/{id_estoque}")
  private ResponseEntity<?> updateClienteById(@PathVariable Integer id_estoque,
      @RequestBody Estoque newStockData) {

    try {
      Optional<Estoque> oldFornecedorData = repository.findById(id_estoque);

      if (oldFornecedorData.isPresent()) {
        Estoque updateFornecedorData = oldFornecedorData.get();
        updateFornecedorData.setNr_quantidade_min(newStockData.getNr_quantidade_min());
        updateFornecedorData.setNr_quantidade_max(newStockData.getNr_quantidade_max());
        updateFornecedorData.setNr_quantidade_atual_items(newStockData.getNr_quantidade_atual_items());

        repository.save(updateFornecedorData);
        return ResponseEntity.status(HttpStatus.OK).body(updateFornecedorData);
      }

      else {
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
