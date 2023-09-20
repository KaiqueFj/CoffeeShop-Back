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
import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;
import com.example.CoffeeShop.repository.NotaFiscalRepository;
import com.example.CoffeeShop.service.Exception.CustomException;
import com.example.CoffeeShop.service.NotaFiscalDTO.NotaFiscalRequestDTO;
import com.example.CoffeeShop.service.NotaFiscalDTO.NotaFiscalResponseDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("invoice")
public class NotaFiscalController {

  @Autowired
  private NotaFiscalRepository repository;

  // Post the Client
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("addNotaFiscal/invoice")
  public ResponseEntity<?> saveInvoice(@RequestBody NotaFiscalRequestDTO data) {
    try {
      NotaFiscal clienteData = new NotaFiscal(data);
      repository.save(clienteData);
      return new ResponseEntity<NotaFiscal>(clienteData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Cliente>(HttpStatus.NOT_MODIFIED);
    }
  }

  // Get all invoices
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllInvoices")
  public ResponseEntity<?> getAllInvoices() {
    try {

      List<NotaFiscalResponseDTO> notaFiscalList = repository.findAll().stream().map(NotaFiscalResponseDTO::new)
          .toList();

      return new ResponseEntity<List<NotaFiscalResponseDTO>>(notaFiscalList, HttpStatus.FOUND);
    } catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getInvoiceById/{id_notaFiscal}")
  public ResponseEntity<?> getInvoiceById(@PathVariable Integer id_notaFiscal) {
    try {
      Optional<NotaFiscal> NotaFiscalData = repository.findById(id_notaFiscal);
      if (!NotaFiscalData.isPresent()) {
        String errorMessage = "Não foi possível encontrar a nota-fiscal. Tente novamente !";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }

      return ResponseEntity.status(HttpStatus.FOUND).body(NotaFiscalData);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("/deleteInvoice/{id_notaFiscal}")
  private ResponseEntity<?> deleteInvoice(@PathVariable Integer id_notaFiscal) {
    try {
      boolean deleted = repository.existsById(id_notaFiscal);

      if (deleted) {
        repository.deleteById(id_notaFiscal);
        return ResponseEntity.ok("Nota-Fiscal deletada com sucesso!");
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível deletar a sua Nota-Fiscal");
      }
    } catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateInvoice/{id_notaFiscal}")
  private ResponseEntity<?> updateClienteById(@PathVariable Integer id_notaFiscal,
      @RequestBody NotaFiscal newNotaFiscalData) {

    try {
      Optional<NotaFiscal> oldNotaFiscalData = repository.findById(id_notaFiscal);

      if (oldNotaFiscalData.isPresent()) {
        NotaFiscal updateNotaFiscalData = oldNotaFiscalData.get();
        updateNotaFiscalData.setNr_notafiscal(newNotaFiscalData.getNr_notafiscal());
        updateNotaFiscalData.setPedidos(newNotaFiscalData.getPedidos());
        repository.save(updateNotaFiscalData);
        return ResponseEntity.status(HttpStatus.OK).body(updateNotaFiscalData);
      } else {
        String errorMessage = "Não foi possível atualizar os dados do cliente. Tente novamente !";
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
