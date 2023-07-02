package com.example.CoffeeShop.model.pedido;

import com.example.CoffeeShop.model.cliente.Cliente;
import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;
import com.example.CoffeeShop.service.PedidoDTO.PedidoRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    // Relantionships
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "T_cliente_id_cliente", referencedColumnName = "id_cliente", updatable = true)
    @JsonBackReference(value = "order-info")
    private Cliente T_cliente_id_cliente;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn(name = "T_Notafiscal_id_Notafiscal", referencedColumnName = "id_Notafiscal", updatable = true)
    @JsonBackReference(value = "recipe-info")
    private NotaFiscal T_Notafiscal_id_Notafiscal;

    public Pedido(PedidoRequestDTO data) {
        this.ds_pedido = data.ds_pedido();
        this.dt_pedido = data.dt_pedido();
        this.vl_pedido = data.vl_pedido();
        this.T_cliente_id_cliente = data.T_cliente_id_cliente();
        this.T_Notafiscal_id_Notafiscal = data.T_Notafiscal_id_Notafiscal();
    }
}
