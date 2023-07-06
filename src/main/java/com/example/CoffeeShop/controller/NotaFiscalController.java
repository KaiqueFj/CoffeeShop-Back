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

import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;
import com.example.CoffeeShop.repository.NotaFiscalRepository;
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
  public NotaFiscal saveInvoice(@RequestBody NotaFiscalRequestDTO data) {
    NotaFiscal clienteData = new NotaFiscal(data);
    repository.save(clienteData);
    return clienteData;

  }

  // Get all invoices
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllInvoices")
  public List<NotaFiscalResponseDTO> getAllInvoices() {

    List<NotaFiscalResponseDTO> notaFiscalList = repository.findAll().stream().map(NotaFiscalResponseDTO::new)
        .toList();

    return notaFiscalList;
  }

  // Get the client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getInvoiceById/{id_notaFiscal}")
  public ResponseEntity<NotaFiscal> getInvoiceById(@PathVariable Integer id_notaFiscal) {
    Optional<NotaFiscal> NotaFiscalData = repository.findById(id_notaFiscal);
    if (NotaFiscalData.isPresent()) {
      return new ResponseEntity<>(NotaFiscalData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("/deleteInvoice/{id_notaFiscal}")
  private void deleteInvoice(@PathVariable Integer id_notaFiscal) {
    try {
      repository.deleteById(id_notaFiscal);
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Update the Client by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateInvoice/{id_notaFiscal}")
  private void updateClienteById(@PathVariable Integer id_notaFiscal,
      @RequestBody NotaFiscal newNotaFiscalData) {

    try {
      Optional<NotaFiscal> oldNotaFiscalData = repository.findById(id_notaFiscal);

      if (oldNotaFiscalData.isPresent()) {
        NotaFiscal updateNotaFiscalData = oldNotaFiscalData.get();
        updateNotaFiscalData.setNr_notafiscal(newNotaFiscalData.getNr_notafiscal());
        updateNotaFiscalData.setPedidos(newNotaFiscalData.getPedidos());
        repository.save(updateNotaFiscalData);
        return;
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
