package com.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voll.api.domain.cliente.Cliente;
import com.voll.api.domain.cliente.ClienteRepository;
import com.voll.api.domain.cliente.DadosAtualizarCliente;
import com.voll.api.domain.cliente.DadosCadastrarCliente;
import com.voll.api.domain.cliente.DadosListaCliente;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastrarCliente dados){
        repository.save(new Cliente(dados));
    }

    @GetMapping
    public Page<DadosListaCliente> listar(@PageableDefault(size = 10, sort={"id"}, page=0 ) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListaCliente::new);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }
    
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizarCliente dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarCliente(dados);
    }
}
