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
  public void saveCliente(@RequestBody ClienteRequestDTO data) {
    Cliente clienteData = new Cliente(data);
    repository.save(clienteData);
    return;

  }

  // Get all clients
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllClients")
  public List<ClienteResponseDTO> getAll() {

    List<ClienteResponseDTO> clienteList = repository.findAll().stream().map(ClienteResponseDTO::new)
        .toList();

    return clienteList;
  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getClienteById/{id_cliente}")
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
  private void deleteCliente(@PathVariable Integer id_cliente) {
    try {
      repository.deleteById(id_cliente);
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateClient/{id_cliente}")
  private void updateClienteById(@PathVariable Integer id_cliente,
      @RequestBody Cliente newFornecedorData) {

    try {
      Optional<Cliente> oldFornecedorData = repository.findById(id_cliente);

      if (oldFornecedorData.isPresent()) {
        Cliente updateFornecedorData = oldFornecedorData.get();
        updateFornecedorData.setNm_cliente(newFornecedorData.getNm_cliente());

        repository.save(updateFornecedorData);
        return;
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
