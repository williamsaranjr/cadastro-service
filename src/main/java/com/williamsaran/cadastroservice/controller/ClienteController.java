package com.williamsaran.cadastroservice.controller;

import com.williamsaran.cadastroservice.model.dto.ClienteDTO;
import com.williamsaran.cadastroservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO encontrarPorId(@PathVariable Long id) {
        return service.encontrarPorId(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO criarConta(@RequestBody ClienteDTO dto) {
        return service.criarConta(dto);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO atualizarConta(@RequestBody ClienteDTO dto) {
        return service.atualizarConta(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        service.deletarPorId(id);
    }

    @PatchMapping(value = "/desativar/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO desativarConta(@PathVariable Long id) {
        return service.desativarConta(id);
    }
}
