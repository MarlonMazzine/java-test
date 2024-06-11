package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Pedido;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Pedido criarPedido(final Pedido pedido) {
        if (pedidoRepository.existsByNumeroControle(pedido.getNumeroControle())) {
            throw new IllegalArgumentException("Número de controle já cadastrado.");
        }

        if (pedido.getDataCadastro() == null) {
            pedido.setDataCadastro(LocalDate.now());
        }

        if (pedido.getQuantidade() == null) {
            pedido.setQuantidade(1);
        }

        BigDecimal valorTotal = pedido.getValorUnitario().multiply(BigDecimal.valueOf(pedido.getQuantidade()));

        if (pedido.getQuantidade() > 5) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.95));
        } else if (pedido.getQuantidade() >= 10) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.90));
        }

        pedido.setValorTotal(valorTotal);

        final Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente não localizado."));
        pedido.setCliente(cliente);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPedidosPorNumeroControle(final String numeroControle) {
        return pedidoRepository.findByNumeroControle(numeroControle);
    }

    public List<Pedido> listarPedidosPorDataCadastro(final LocalDate dataCadastro) {
        return pedidoRepository.findByDataCadastro(dataCadastro);
    }

}