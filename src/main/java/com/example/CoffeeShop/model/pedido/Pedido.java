package com.example.CoffeeShop.model.pedido;

import com.example.CoffeeShop.service.PedidoDTO.PedidoRequestDTO;

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

@Table(name = "T_Pedido")
@Entity(name = "T_Pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_Pedido")

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_Pedido;
    String ds_pedido;
    String dt_pedido;
    String vl_pedido;

    public Pedido(PedidoRequestDTO data) {
        this.ds_pedido = data.ds_pedido();
        this.dt_pedido = data.dt_pedido();
        this.vl_pedido = data.vl_pedido();
    }
}
