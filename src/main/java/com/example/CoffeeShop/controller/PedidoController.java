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

import com.example.CoffeeShop.model.pedido.Pedido;
import com.example.CoffeeShop.repository.PedidoRepository;
import com.example.CoffeeShop.service.Exception.CustomException;
import com.example.CoffeeShop.service.PedidoDTO.PedidoRequestDTO;
import com.example.CoffeeShop.service.PedidoDTO.PedidoResponseDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pedido")
public class PedidoController {

  @Autowired
  private PedidoRepository repository;

  // Post the Client
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("addPedido/Pedido")
  public ResponseEntity<?> saveOrder(@RequestBody PedidoRequestDTO data) {
    try {
      Pedido pedidoData = new Pedido(data);
      repository.save(pedidoData);

      return new ResponseEntity<>(pedidoData, HttpStatus.CREATED);

    } catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

  }

  // Get all invoices
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllOrders")

  public ResponseEntity<?> getAllOrders() {
    try {
      List<PedidoResponseDTO> pedidoList = repository.findAll().stream().map(PedidoResponseDTO::new)
          .toList();

      return new ResponseEntity<>(pedidoList, HttpStatus.OK);
    } catch (Exception e) {
      String errorMessage = "Não foi possível verificar a lista de pedidos, tente novamente ! " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getOrderById/{id_Pedido}")
  public ResponseEntity<?> getOrderById(@PathVariable Integer id_Pedido) {
    try {
      Optional<Pedido> PedidoData = repository.findById(id_Pedido);
      if (!PedidoData.isPresent()) {
        String errorMessage = "Não foi possível encontrar o seu pedido. Tente novamente !";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }

      return ResponseEntity.status(HttpStatus.FOUND).body(PedidoData);

    } catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("/deleteOrder/{id_Pedido}")
  private ResponseEntity<String> deleteOrderById(@PathVariable Integer id_Pedido) {
    boolean deleted = repository.existsById(id_Pedido);

    if (deleted) {
      repository.deleteById(id_Pedido);
      return ResponseEntity.ok("Pedido deletado com sucesso !");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o pedido para ser deletado!");
    }
  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateOrderById/{id_Pedido}")
  private ResponseEntity<?> updateOrderById(@PathVariable Integer id_Pedido,
      @RequestBody Pedido newPedidoData) {

    try {
      Optional<Pedido> oldPedidoData = repository.findById(id_Pedido);

      if (oldPedidoData.isPresent()) {
        Pedido updatePedidoData = oldPedidoData.get();
        updatePedidoData.setDs_pedido(newPedidoData.getDs_pedido());
        updatePedidoData.setDt_pedido(newPedidoData.getDt_pedido());
        updatePedidoData.setVl_pedido(newPedidoData.getVl_pedido());
        updatePedidoData.setT_cliente_id_cliente(newPedidoData.getT_cliente_id_cliente());
        updatePedidoData.setT_notafiscal_id_notafiscal(newPedidoData.getT_notafiscal_id_notafiscal());
        repository.save(updatePedidoData);
        return ResponseEntity.status(HttpStatus.FOUND).body(updatePedidoData);

      } else {
        String errorMessage = "Não foi possível atualizar o pedido. Tente novamente!";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

      }
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }
}
