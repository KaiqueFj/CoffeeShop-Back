package com.example.CoffeeShop.model.Estoque;

import com.example.CoffeeShop.service.EstoqueDTO.EstoqueRequestDTO;

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

  public Estoque(EstoqueRequestDTO data) {
    this.nr_quantidade_min = data.nr_quantidade_min();
    this.nr_quantidade_max = data.nr_quantidade_max();
    this.nr_quantidade_atual_items = data.nr_quantidade_atual_items();
  }

}
