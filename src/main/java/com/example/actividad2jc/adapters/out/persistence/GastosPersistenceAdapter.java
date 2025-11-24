/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.actividad2jc.adapters.out.persistence;

import com.example.actividad2jc.adapters.out.persistence.repository.GastosJpaRepository;
import com.example.actividad2jc.domain.model.Gastos;
import com.example.actividad2jc.domain.port.out.GastoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
/**
 *
 * @author Jean Carlos
 */
public class GastosPersistenceAdapter implements GastoRepositoryPort {

    private final GastosJpaRepository repository;

    public GastosPersistenceAdapter(GastosJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Gastos save(Gastos gastos) {
        GastosEntity entity = toEntity(gastos);
        GastosEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Gastos> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Gastos findById(Long id) {
        return repository.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Gastos toDomain(GastosEntity e) {
        return new Gastos(
                e.getId(), e.getFecha(), e.getValorTotalSinIVA(), e.getIvaTotal(),
                e.getValorTotalConIVA(), e.getNombreUsuario(), e.getLugar(), e.getDescripcion()
        );
    }

    private GastosEntity toEntity(Gastos d) {
        GastosEntity e = new GastosEntity();
        e.setId(d.getId());
        e.setFecha(d.getFecha());
        e.setValorTotalSinIVA(d.getValorTotalSinIVA());
        e.setIvaTotal(d.getIvaTotal());
        e.setValorTotalConIVA(d.getValorTotalConIVA());
        e.setNombreUsuario(d.getNombreUsuario());
        e.setLugar(d.getLugar());
        e.setDescripcion(d.getDescripcion());
        return e;
    }
}