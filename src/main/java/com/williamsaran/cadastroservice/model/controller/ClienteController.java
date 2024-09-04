package com.williamsaran.cadastroservice.model.controller;

import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.repository.ClienteRepository;
import com.williamsaran.cadastroservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping("{id}")
    public ClienteDTO encontrarPorId(@PathVariable Long id) {
        return service.encontrarPorId(id);
    }

    @PostMapping
    public ClienteDTO criarConta(ClienteDTO dto) {
        return service.criarConta(dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        service.deletarPorId(id);
    }
}
