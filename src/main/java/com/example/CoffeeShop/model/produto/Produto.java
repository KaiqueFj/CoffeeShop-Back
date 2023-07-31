package com.example.CoffeeShop.model.produto;

import com.example.CoffeeShop.model.Estoque.Estoque;
import com.example.CoffeeShop.model.pedido.Pedido;
import com.example.CoffeeShop.service.ProdutoDTO.ProdutoRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
  @JoinColumn(name = "T_pedido_id_pedido", referencedColumnName = "id_Pedido")
  private Pedido T_pedido_id_pedido;

  @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH }, fetch = FetchType.EAGER)
  @JoinColumn(name = "T_estoque_id_estoque", referencedColumnName = "id_estoque")
  private Estoque T_estoque_id_estoque;

  public Produto(ProdutoRequestDTO data) {
    this.nm_produto = data.nm_produto();
    this.vl_produto = data.vl_produto();
    this.qt_produto = data.qt_produto();
    this.T_pedido_id_pedido = data.T_pedido_id_pedido();
    this.T_estoque_id_estoque = data.T_estoque_id_estoque();
  }

}
