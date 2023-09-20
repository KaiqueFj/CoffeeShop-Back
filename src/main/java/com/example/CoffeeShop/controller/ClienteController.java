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
import com.example.CoffeeShop.repository.ClienteRepository;
import com.example.CoffeeShop.service.Exception.CustomException;
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
  public ResponseEntity<?> saveCliente(@RequestBody ClienteRequestDTO data) {
    try {
      Cliente clienteData = new Cliente(data);
      repository.save(clienteData);
      return new ResponseEntity<Cliente>(clienteData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

  }

  // Get all clients
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllClients")
  public ResponseEntity<?> getAll() {
    try {
      List<ClienteResponseDTO> clienteList = repository.findAll().stream().map(ClienteResponseDTO::new)
          .toList();

      return new ResponseEntity<List<ClienteResponseDTO>>(clienteList, HttpStatus.FOUND);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getClient/{id_cliente}")
  public ResponseEntity<?> getBookById(@PathVariable Integer id_cliente) {
    try {
      Optional<Cliente> clienteData = repository.findById(id_cliente);
      if (!clienteData.isPresent()) {
        String errorMessage = "Não foi possível encontrar o cliente. Tente novamente !";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }
      return ResponseEntity.status(HttpStatus.FOUND).body(clienteData);
    } catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteClient/{id_cliente}")
  private ResponseEntity<?> deleteCliente(@PathVariable Integer id_cliente) {
    try {
      boolean deleted = repository.existsById(id_cliente);

      if (deleted) {
        repository.deleteById(id_cliente);
        return ResponseEntity.ok("Cliente deletado com sucesso !");
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Não foi possível encontrar o cliente para ser deletado");
      }
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateClient/{id_cliente}")
  private ResponseEntity<?> updateClienteById(@PathVariable Integer id_cliente,
      @RequestBody Cliente newClienteData) {

    try {
      Optional<Cliente> oldClienteData = repository.findById(id_cliente);

      if (oldClienteData.isPresent()) {
        Cliente updateClienteData = oldClienteData.get();
        updateClienteData.setNm_cliente(newClienteData.getNm_cliente());

        repository.save(updateClienteData);
        return ResponseEntity.status(HttpStatus.OK).body(updateClienteData);
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
