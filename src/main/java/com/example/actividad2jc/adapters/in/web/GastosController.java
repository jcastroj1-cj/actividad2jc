/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.actividad2jc.adapters.in.web;
import com.example.actividad2jc.application.usecase.GastosUseCase;
import com.example.actividad2jc.domain.model.Gastos;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
/**
 *
 * @author Jean Carlos
 */
public class GastosController {
    
    private final GastosUseCase useCase;

    public GastosController(GastosUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Gastos create(@RequestBody Gastos gasto) {
        return useCase.save(gasto);
    }

    @GetMapping
    public List<Gastos> findAll() {
        return useCase.findAll();
    }

    @GetMapping("/{id}")
    public Gastos findById(@PathVariable Long id) {
        return useCase.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}
