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

import com.example.CoffeeShop.model.produto.Produto;
import com.example.CoffeeShop.repository.ProdutoRepository;
import com.example.CoffeeShop.service.ProdutoDTO.ProdutoRequestDTO;
import com.example.CoffeeShop.service.ProdutoDTO.ProdutoResponseDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produto")
public class ProdutoController {

  @Autowired
  private ProdutoRepository repository;

  // Post the Product
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("addProduct/product")
  public ResponseEntity<Produto> saveProduto(@RequestBody ProdutoRequestDTO data) {
    try {
      Produto produtoData = new Produto(data);
      repository.save(produtoData);
      return new ResponseEntity<Produto>(produtoData, HttpStatus.OK);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Produto>(HttpStatus.BAD_REQUEST);
    }
  }

  // Get all products
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllProducts")
  public ResponseEntity<List<ProdutoResponseDTO>> getAll() {
    try {
      List<ProdutoResponseDTO> produtoList = repository.findAll().stream().map(ProdutoResponseDTO::new)
          .toList();
      return new ResponseEntity<List<ProdutoResponseDTO>>(produtoList, HttpStatus.FOUND);
    } catch (Exception e) {
      return new ResponseEntity<List<ProdutoResponseDTO>>(HttpStatus.NOT_FOUND);
    }

  }

  // Get the product by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getProdutoById/{id_produto}")
  public ResponseEntity<Produto> getBookById(@PathVariable Integer id_produto) {
    Optional<Produto> produtoData = repository.findById(id_produto);
    if (produtoData.isPresent()) {
      return new ResponseEntity<>(produtoData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the Product by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteProduct/{id_produto}")
  private ResponseEntity<String> deleteProduct(@PathVariable Integer id_produto) {
    try {
      boolean deleted = repository.existsById(id_produto);

      if (deleted) {
        repository.deleteById(id_produto);
        return ResponseEntity.ok("Resouce Deleted successfully");
      }

      else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Resouce Deleted successfully");
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Resouce Deleted successfully");

    }
  }

  // Update the Product by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateProduct/{id_produto}")
  private ResponseEntity<Produto> updateProdutoById(@PathVariable Integer id_produto,
      @RequestBody Produto newProdutoData) {

    try {
      Optional<Produto> oldProdutoData = repository.findById(id_produto);

      if (oldProdutoData.isPresent()) {
        Produto updateProdutoData = oldProdutoData.get();
        updateProdutoData.setNm_produto(newProdutoData.getNm_produto());
        updateProdutoData.setVl_produto(newProdutoData.getVl_produto());
        updateProdutoData.setQt_produto(newProdutoData.getQt_produto());

        repository.save(updateProdutoData);
        return new ResponseEntity<Produto>(updateProdutoData, HttpStatus.OK);
      }

      else {
        return new ResponseEntity<Produto>(HttpStatus.NOT_MODIFIED);

      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Produto>(HttpStatus.NOT_MODIFIED);
    }
  }
}
