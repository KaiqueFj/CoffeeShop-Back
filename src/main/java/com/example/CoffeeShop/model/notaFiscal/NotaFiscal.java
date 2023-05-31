package com.example.CoffeeShop.model.notaFiscal;

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

@Table(name = "T_notaFiscal ")
@Entity(name = "T_notaFiscal ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_notaFiscal")

public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_notaFiscal;
    private Integer nr_notaFiscal;

    public NotaFiscal(NotaFiscalRequestDTO data) {
        this.nr_notaFiscal = data.nr_notaFiscal();
    }

}
