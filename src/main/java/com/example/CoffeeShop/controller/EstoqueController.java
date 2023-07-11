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
import com.example.CoffeeShop.repository.EstoqueRepository;
import com.example.CoffeeShop.service.EstoqueDTO.EstoqueRequestDTO;
import com.example.CoffeeShop.service.EstoqueDTO.EstoqueResponseDTO;

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
  public ResponseEntity<Estoque> saveStock(@RequestBody EstoqueRequestDTO data) {
    try {
      Estoque estoqueData = new Estoque(data);
      repository.save(estoqueData);
      return new ResponseEntity<Estoque>(estoqueData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Estoque>(HttpStatus.BAD_REQUEST);
    }

  }

  // Get all clients
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllStock")
  public ResponseEntity<List<EstoqueResponseDTO>> getAll() {
    try {
      List<EstoqueResponseDTO> stockList = repository.findAll().stream().map(EstoqueResponseDTO::new)
          .toList();

      return new ResponseEntity<List<EstoqueResponseDTO>>(stockList, HttpStatus.FOUND);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<List<EstoqueResponseDTO>>(HttpStatus.BAD_REQUEST);
    }

  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getStock/{id_estoque}")
  public ResponseEntity<Estoque> getBookById(@PathVariable Integer id_estoque) {
    Optional<Estoque> estoqueData = repository.findById(id_estoque);
    if (estoqueData.isPresent()) {
      return new ResponseEntity<>(estoqueData.get(), HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteStock/{id_estoque}")
  private ResponseEntity<String> deleteCliente(@PathVariable Integer id_estoque) {
    try {
      boolean deleted = repository.existsById(id_estoque);

      if (deleted) {
        repository.deleteById(id_estoque);
        return ResponseEntity.ok("Resource deleted successfully");
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }
  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateStock/{id_estoque}")
  private ResponseEntity<Estoque> updateClienteById(@PathVariable Integer id_estoque,
      @RequestBody Estoque newStockData) {

    try {
      Optional<Estoque> oldFornecedorData = repository.findById(id_estoque);

      if (oldFornecedorData.isPresent()) {
        Estoque updateFornecedorData = oldFornecedorData.get();
        updateFornecedorData.setNr_quantidade_min(newStockData.getNr_quantidade_min());
        updateFornecedorData.setNr_quantidade_max(newStockData.getNr_quantidade_max());
        updateFornecedorData.setNr_quantidade_atual_items(newStockData.getNr_quantidade_atual_items());

        repository.save(updateFornecedorData);
        return new ResponseEntity<Estoque>(updateFornecedorData, HttpStatus.OK);
      }

      else {
        return new ResponseEntity<Estoque>(HttpStatus.BAD_REQUEST);
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Estoque>(HttpStatus.NOT_MODIFIED);
    }
  }
}
