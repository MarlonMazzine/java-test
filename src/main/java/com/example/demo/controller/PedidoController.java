package com.example.demo.controller;

import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private static final int QUANTIDADE_MAXIMA = 10;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<String> criarPedido(@RequestBody List<Pedido> pedidos) {
        if (pedidos.size() > QUANTIDADE_MAXIMA) {
            return ResponseEntity.status(400).body(String.format("Quantidade de pedidos não pode ser superior a %d.", QUANTIDADE_MAXIMA));
        } else if (pedidos.isEmpty()) {
            return ResponseEntity.status(400).body("Deve ser informado ao menos 1 pedido.");
        }

        for (Pedido pedido : pedidos) {
            pedidoService.criarPedido(pedido);
        }

        return ResponseEntity.status(200).body("Pedidos adicionados com sucesso.");
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Pedido> listarPedidos(@RequestParam(required = false) String numeroControle,
                                      @RequestParam(required = false) LocalDate dataCadastro) {
        if (numeroControle != null) {
            return pedidoService.listarPedidosPorNumeroControle(numeroControle);
        } else if (dataCadastro != null) {
            return pedidoService.listarPedidosPorDataCadastro(dataCadastro);
        } else {
            return pedidoService.listarPedidos();
        }
    }
}
