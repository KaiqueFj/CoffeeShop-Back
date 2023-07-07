package com.example.CoffeeShop.model.pedido;

import java.util.ArrayList;
import java.util.List;

import com.example.CoffeeShop.model.cliente.Cliente;
import com.example.CoffeeShop.model.notaFiscal.NotaFiscal;
import com.example.CoffeeShop.model.produto.Produto;
import com.example.CoffeeShop.service.PedidoDTO.PedidoRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "T_pedido_id_pedido")
    @JsonBackReference
    private List<Produto> produtos = new ArrayList<Produto>();

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "T_cliente_id_cliente", referencedColumnName = "id_cliente")
    private Cliente T_cliente_id_cliente;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "T_notafiscal_id_notafiscal", referencedColumnName = "id_Notafiscal")
    private NotaFiscal T_notafiscal_id_notafiscal;

    public Pedido(PedidoRequestDTO data) {
        this.ds_pedido = data.ds_pedido();
        this.dt_pedido = data.dt_pedido();
        this.vl_pedido = data.vl_pedido();
        this.T_cliente_id_cliente = data.T_cliente_id_cliente();
        this.T_notafiscal_id_notafiscal = data.T_notafiscal_id_notafiscal();
        this.produtos = data.produtos();
    }
}
