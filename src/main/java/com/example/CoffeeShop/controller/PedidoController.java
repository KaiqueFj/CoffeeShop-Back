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
import com.example.CoffeeShop.model.pedido.Pedido;
import com.example.CoffeeShop.repository.ClienteRepository;
import com.example.CoffeeShop.repository.PedidoRepository;
import com.example.CoffeeShop.service.PedidoDTO.PedidoRequestDTO;
import com.example.CoffeeShop.service.PedidoDTO.PedidoResponseDTO;
import com.example.CoffeeShop.service.clienteDTO.ClienteRequestDTO;
import com.example.CoffeeShop.service.clienteDTO.ClienteResponseDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cliente")
public class PedidoController {

  @Autowired
  private PedidoRepository repository;

  // Post the Client
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("addPedido/Pedido")
  public Pedido saveOrder(@RequestBody PedidoRequestDTO data) {

    Pedido pedidoData = new Pedido(data);
    repository.save(pedidoData);
    return pedidoData;

  }

  // Get all invoices
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllOrders")
  public List<PedidoResponseDTO> getAllOrders() {

    List<PedidoResponseDTO> pedidoList = repository.findAll().stream().map(PedidoResponseDTO::new)
        .toList();

    return pedidoList;
  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getOrderById/{id_Pedido}")
  public ResponseEntity<Pedido> getOrderById(@PathVariable Integer id_Pedido) {
    Optional<Pedido> PedidoData = repository.findById(id_Pedido);
    if (PedidoData.isPresent()) {
      return new ResponseEntity<>(PedidoData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteOrder/{id_Pedido}")
  private void deleteOrderById(@PathVariable Integer id_Pedido) {
    try {
      repository.deleteById(id_Pedido);
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateOrderById/{id_Pedido}")
  private void updateOrderById(@PathVariable Integer id_Pedido,
      @RequestBody Pedido newPedidoData) {

    try {
      Optional<Pedido> oldPedidoData = repository.findById(id_Pedido);

      if (oldPedidoData.isPresent()) {
        Pedido updatePedidoData = oldPedidoData.get();
        updatePedidoData.setDs_pedido(newPedidoData.getDs_pedido());
        updatePedidoData.setDt_pedido(newPedidoData.getDt_pedido());
        updatePedidoData.setVl_pedido(newPedidoData.getVl_pedido());
        repository.save(updatePedidoData);
        return;
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
