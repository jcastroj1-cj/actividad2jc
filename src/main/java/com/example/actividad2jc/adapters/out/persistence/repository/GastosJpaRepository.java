/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.actividad2jc.adapters.out.persistence.repository;

import com.example.actividad2jc.adapters.out.persistence.GastosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Jean Carlos
 */
public interface GastosJpaRepository extends JpaRepository<GastosEntity, Long> {
}