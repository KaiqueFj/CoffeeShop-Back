package com.example.CoffeeShop.model;

import com.example.CoffeeShop.produto.ProdutoRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "T_produto")
@Entity(name = "T_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_produto")

public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_produto;
  private String nm_produto;
  private String vl_produto;
  private Integer qt_produto;

  public Produto(ProdutoRequestDTO data) {
    this.nm_produto = data.nm_produto();
    this.vl_produto = data.vl_produto();
    this.qt_produto = data.qt_produto();
  }

}
