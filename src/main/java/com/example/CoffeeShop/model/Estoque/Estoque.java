package com.example.CoffeeShop.model.Estoque;

import java.util.ArrayList;
import java.util.List;

import com.example.CoffeeShop.model.produto.Produto;
import com.example.CoffeeShop.service.EstoqueDTO.EstoqueRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "T_estoque")
@Entity(name = "T_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_estoque")

public class Estoque {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_estoque;
  private Integer nr_quantidade_min;
  private Integer nr_quantidade_max;
  private Integer nr_quantidade_atual_items;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "T_estoque_id_estoque")
  @JsonBackReference
  private List<Produto> produtos = new ArrayList<Produto>();

  public Estoque(EstoqueRequestDTO data) {
    this.nr_quantidade_min = data.nr_quantidade_min();
    this.nr_quantidade_max = data.nr_quantidade_max();
    this.nr_quantidade_atual_items = data.nr_quantidade_atual_items();
    this.produtos = data.produtos();
  }

}
