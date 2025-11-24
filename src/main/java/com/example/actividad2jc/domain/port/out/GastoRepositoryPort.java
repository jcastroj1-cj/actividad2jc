/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.actividad2jc.domain.port.out;


import com.example.actividad2jc.domain.model.Gastos;

import java.util.List;
/**
 *
 * @author Jean Carlos
 */
public interface GastoRepositoryPort {
    
    Gastos save(Gastos gastos);
    List<Gastos> findAll();
    Gastos findById(Long id);
    void deleteById(Long id);
}

