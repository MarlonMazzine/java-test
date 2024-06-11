package com.example.demo.repository;

import com.example.demo.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsByNumeroControle(String numeroControle);

    List<Pedido> findByNumeroControle(String numeroControle);

    List<Pedido> findByDataCadastro(LocalDate dataCadastro);
}