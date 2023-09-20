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
import com.example.CoffeeShop.service.Exception.CustomException;
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
  public ResponseEntity<?> saveProduto(@RequestBody ProdutoRequestDTO data) {
    try {
      Produto produtoData = new Produto(data);
      repository.save(produtoData);
      return new ResponseEntity<Produto>(produtoData, HttpStatus.OK);
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }

  // Get all products
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllProducts")
  public ResponseEntity<?> getAll() {
    try {
      List<ProdutoResponseDTO> produtoList = repository.findAll().stream().map(ProdutoResponseDTO::new)
          .toList();
      return new ResponseEntity<List<ProdutoResponseDTO>>(produtoList, HttpStatus.FOUND);
    } catch (Exception e) {
      String errorMessage = "Não foi possível verificar a lista de Produtos, tente novamente ! " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

  }

  // Get the product by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getProdutoById/{id_produto}")
  public ResponseEntity<?> getBookById(@PathVariable Integer id_produto) {
    try {
      Optional<Produto> produtoData = repository.findById(id_produto);
      if (!produtoData.isPresent()) {
        String errorMessage = "Não foi possível encontrar o seu pedido. Tente novamente !";
        CustomException errorResponse = new CustomException(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }
      return ResponseEntity.status(HttpStatus.FOUND).body(produtoData);

    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

  }

  // Delete the Product by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("deleteProduct/{id_produto}")
  private ResponseEntity<?> deleteProduct(@PathVariable Integer id_produto) {
    try {
      boolean deleted = repository.existsById(id_produto);

      if (deleted) {
        repository.deleteById(id_produto);
        return ResponseEntity.ok("Produto deletado com sucesso!");
      }

      else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar o produto, tente novamente!");
      }
    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

  }

  // Update the Product by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateProduct/{id_produto}")
  private ResponseEntity<?> updateProdutoById(@PathVariable Integer id_produto,
      @RequestBody Produto newProdutoData) {

    try {
      Optional<Produto> oldProdutoData = repository.findById(id_produto);

      if (oldProdutoData.isPresent()) {
        Produto updateProdutoData = oldProdutoData.get();
        updateProdutoData.setNm_produto(newProdutoData.getNm_produto());
        updateProdutoData.setVl_produto(newProdutoData.getVl_produto());
        updateProdutoData.setQt_produto(newProdutoData.getQt_produto());
        updateProdutoData.setT_pedido_id_pedido(newProdutoData.getT_pedido_id_pedido());
        updateProdutoData.setT_estoque_id_estoque(newProdutoData.getT_estoque_id_estoque());

        repository.save(updateProdutoData);
        return ResponseEntity.status(HttpStatus.FOUND).body(updateProdutoData);
      }

      else {
        String errorMessage = "Não foi possível atualizar o Produto. Tente novamente!";
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
