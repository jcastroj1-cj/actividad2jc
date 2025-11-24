
 package com.example.actividad2jc.adapters.out.persistence;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
@Data   // Lombok crea getters, setters, toString, equals y hashCode
public class GastosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Double valorTotalSinIVA;
    private Double ivaTotal;
    private Double valorTotalConIVA;
    private String nombreUsuario;
    private String lugar;
    private String descripcion;
}
