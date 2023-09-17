package com.treina.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.treina.domain.usuario.UsuarioRepository;
import com.treina.models.usuario.UsuarioModel;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioModel model) {
        var usuario = new UsuarioModel();

        return new ResponseEntity<UsuarioModel>(repository.save(usuario), HttpStatus.CREATED);
    }
    
    /** 
    @GetMapping // Lista 4 Capitulo 4 video 4
    public ResponseEntity<Page<UsuarioModel>> 
        listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(UsuarioModel::new);

        return ResponseEntity.ok(page);
    }
     */
    
}
