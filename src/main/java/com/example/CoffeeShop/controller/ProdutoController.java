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

import com.example.CoffeeShop.model.Produto;
import com.example.CoffeeShop.model.produto.ProdutoRepository;
import com.example.CoffeeShop.model.produto.ProdutoRequestDTO;
import com.example.CoffeeShop.model.produto.ProdutoResponseDTO;

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
  public Produto saveProduto(@RequestBody ProdutoRequestDTO data) {
    Produto produtoData = new Produto(data);
    repository.save(produtoData);
    return produtoData;

  }

  // Get all products
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAllProducts")
  public List<ProdutoResponseDTO> getAll() {

    List<ProdutoResponseDTO> produtoList = repository.findAll().stream().map(ProdutoResponseDTO::new)
        .toList();

    return produtoList;
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
  private void deleteProduct(@PathVariable Integer id_produto) {
    try {
      repository.deleteById(id_produto);
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Update the Product by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateProduct/{id_produto}")
  private void updateProdutoById(@PathVariable Integer id_produto,
      @RequestBody Produto newProdutoData) {

    try {
      Optional<Produto> oldProdutoData = repository.findById(id_produto);

      if (oldProdutoData.isPresent()) {
        Produto updateProdutoData = oldProdutoData.get();
        updateProdutoData.setNm_produto(newProdutoData.getNm_produto());
        updateProdutoData.setVl_produto(newProdutoData.getVl_produto());
        updateProdutoData.setQt_produto(newProdutoData.getQt_produto());

        repository.save(updateProdutoData);
        return;
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
