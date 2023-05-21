package com.example.CoffeeShop.model;

import com.example.CoffeeShop.Fornecedor.FornecedorRequestDTO;

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

@Table(name = "T_fornecedor")
@Entity(name = "T_fornecedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_fornecedor")

public class Fornecedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_fornecedor;
  private String nm_fornecedor;
  private String nm_email;

  public Fornecedor(FornecedorRequestDTO data) {
    this.nm_fornecedor = data.nm_fornecedor();
    this.nm_email = data.nm_email();
  }

  public void setEmail(String nm_email2) {
  }

}
