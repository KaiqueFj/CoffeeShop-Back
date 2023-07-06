package com.example.CoffeeShop.model.notaFiscal;

import com.example.CoffeeShop.model.pedido.Pedido;
import com.example.CoffeeShop.service.NotaFiscalDTO.NotaFiscalRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "T_Notafiscal ")
@Entity(name = "T_Notafiscal ")
@JsonIgnoreProperties("pedidos")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_Notafiscal")

public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Notafiscal;
    private Integer nr_notafiscal;

    @OneToOne(mappedBy = "T_notafiscal_id_notafiscal")
    @JsonBackReference(value = "recipe-info")
    private Pedido pedidos;

    public NotaFiscal(NotaFiscalRequestDTO data) {
        this.nr_notafiscal = data.nr_notafiscal();
        this.pedidos = data.pedidos();
    }

}
