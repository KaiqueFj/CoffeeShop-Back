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

import com.example.CoffeeShop.Fornecedor.FornecedorRepository;
import com.example.CoffeeShop.Fornecedor.FornecedorRequestDTO;
import com.example.CoffeeShop.Fornecedor.FornecedorResponseDTO;
import com.example.CoffeeShop.model.Fornecedor;

import java.util.List;
import java.util.Optional;

@RequestMapping("dealer")
@RestController
public class FornecedorController {

  @Autowired
  private FornecedorRepository repository;

  // Save the dealer
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/postFornecedor")
  public void saveFornecedor(@RequestBody FornecedorRequestDTO data) {

    try {
      Fornecedor fornecedorData = new Fornecedor(data);
      repository.save(fornecedorData);
      return;
    }

    catch (Exception e) {
      e.printStackTrace();
    }

  }

  // Get all dealers
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getAllFornecedor")
  public List<FornecedorResponseDTO> getAll() {

    List<FornecedorResponseDTO> fornecedorList = repository.findAll().stream().map(FornecedorResponseDTO::new)
        .toList();

    return fornecedorList;
  }

  // Get the Dealer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getdealerById/{id_fornecedor}")
  public ResponseEntity<Fornecedor> getBookById(@PathVariable Integer id_fornecedor) {
    Optional<Fornecedor> fornecedorData = repository.findById(id_fornecedor);
    if (fornecedorData.isPresent()) {
      return new ResponseEntity<>(fornecedorData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the Dealer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("fornecedor/{id_fornecedor}")
  private void deleteFornecedor(@PathVariable Integer id_fornecedor) {
    try {
      repository.deleteById(id_fornecedor);
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Update the Dealer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateFornecedor/{id_fornecedor}")
  private void updateFornecedorById(@PathVariable Integer id_fornecedor,
      @RequestBody Fornecedor newFornecedorData) {

    try {
      Optional<Fornecedor> oldFornecedorData = repository.findById(id_fornecedor);

      if (oldFornecedorData.isPresent()) {
        Fornecedor updateFornecedorData = oldFornecedorData.get();
        updateFornecedorData.setNm_fornecedor(newFornecedorData.getNm_fornecedor());
        updateFornecedorData.setNm_email(newFornecedorData.getNm_email());

        repository.save(updateFornecedorData);
        return;
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
