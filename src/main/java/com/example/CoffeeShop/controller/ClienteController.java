package com.example.CoffeeShop.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.example.CoffeeShop.repository.ClienteRepository;
import com.example.CoffeeShop.service.clienteDTO.ClienteRequestDTO;
import com.example.CoffeeShop.service.clienteDTO.ClienteResponseDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cliente")
public class ClienteController {

  @Autowired
  private ClienteRepository repository;

  // Post the Client
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("addUser/client")
  public ResponseEntity<Cliente> saveCliente(@RequestBody ClienteRequestDTO data) {
    try {
      Cliente clienteData = new Cliente(data);
      repository.save(clienteData);
      return new ResponseEntity<Cliente>(clienteData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Cliente>(HttpStatus.BAD_REQUEST);
    }

  }

  // Get all clients
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllClients")
  public ResponseEntity<List<ClienteResponseDTO>> getAll() {
    try {
      List<ClienteResponseDTO> clienteList = repository.findAll().stream().map(ClienteResponseDTO::new)
          .toList();

      return new ResponseEntity<List<ClienteResponseDTO>>(clienteList, HttpStatus.FOUND);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<List<ClienteResponseDTO>>(HttpStatus.BAD_REQUEST);
    }

  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getClient/{id_cliente}")
  public ResponseEntity<Cliente> getBookById(@PathVariable Integer id_cliente) {
    Optional<Cliente> clienteData = repository.findById(id_cliente);
    if (clienteData.isPresent()) {
      return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteClient/{id_cliente}")
  private ResponseEntity<String> deleteCliente(@PathVariable Integer id_cliente) {
    try {
      boolean deleted = repository.existsById(id_cliente);

      if (deleted) {
        repository.deleteById(id_cliente);
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
  @PutMapping("/updateClient/{id_cliente}")
  private ResponseEntity<Cliente> updateClienteById(@PathVariable Integer id_cliente,
      @RequestBody Cliente newFornecedorData) {

    try {
      Optional<Cliente> oldFornecedorData = repository.findById(id_cliente);

      if (oldFornecedorData.isPresent()) {
        Cliente updateFornecedorData = oldFornecedorData.get();
        updateFornecedorData.setNm_cliente(newFornecedorData.getNm_cliente());

        repository.save(updateFornecedorData);
        return new ResponseEntity<Cliente>(updateFornecedorData, HttpStatus.OK);
      }

      else {
        return new ResponseEntity<Cliente>(HttpStatus.BAD_REQUEST);
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Cliente>(HttpStatus.NOT_MODIFIED);
    }
  }
}
