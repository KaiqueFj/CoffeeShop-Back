package com.example.CoffeeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShop.cliente.Cliente;
import com.example.CoffeeShop.cliente.ClienteRepository;
import com.example.CoffeeShop.cliente.ClienteRequestDTO;
import com.example.CoffeeShop.cliente.ClienteResponseDTO;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

  @Autowired
  private ClienteRepository repository;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping
  public void saveCliente(@RequestBody ClienteRequestDTO data) {
    Cliente clienteData = new Cliente(data);
    repository.save(clienteData);
    return;

  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping
  public List<ClienteResponseDTO> getAll() {

    List<ClienteResponseDTO> clienteList = repository.findAll().stream().map(ClienteResponseDTO::new)
        .toList();

    return clienteList;
  }
}
