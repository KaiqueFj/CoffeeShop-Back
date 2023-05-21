package com.example.CoffeeShop.model;

import com.example.CoffeeShop.cliente.ClienteRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "T_cliente")
@Entity(name = "T_cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_cliente")

public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_cliente;
  private String nm_cliente;

  public Cliente(ClienteRequestDTO data) {
    this.nm_cliente = data.nm_cliente();
  }

}
