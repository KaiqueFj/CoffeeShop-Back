package com.example.CoffeeShop.model.cliente;

import java.util.ArrayList;
import java.util.List;

import com.example.CoffeeShop.model.pedido.Pedido;
import com.example.CoffeeShop.service.clienteDTO.ClienteRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

@Table(name = "T_cliente")
@Entity(name = "T_cliente")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_cliente")

public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_cliente;
  private String nm_cliente;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "T_cliente_id_cliente")
  @JsonBackReference(value = "order-info")
  private List<Pedido> pedidos = new ArrayList<Pedido>();

  public Cliente(ClienteRequestDTO data) {
    this.nm_cliente = data.nm_cliente();
    this.pedidos = data.pedidos();
  }

}
