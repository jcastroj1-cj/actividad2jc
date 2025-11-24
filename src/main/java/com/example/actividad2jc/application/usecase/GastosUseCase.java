/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.actividad2jc.application.usecase;
import com.example.actividad2jc.domain.model.Gastos;
import com.example.actividad2jc.domain.port.out.GastoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 *
 * @author Jean Carlos
 */
public class GastosUseCase {
    
    private final GastoRepositoryPort repository;

    public GastosUseCase(GastoRepositoryPort repository) {
        this.repository = repository;
    }

    public Gastos save(Gastos gasto) {
        return repository.save(gasto);
    }

    public List<Gastos> findAll() {
        return repository.findAll();
    }

    public Gastos findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

