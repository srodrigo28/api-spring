package com.treina.domain.usuario;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.treina.models.usuario.UsuarioModel;

import lombok.Data;

@Data
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Cadastrar :: CREATED;
    public ResponseEntity<?> cadastrar(UsuarioModel model){
        return new ResponseEntity<UsuarioModel>(repository.save(model), HttpStatus.CREATED);
    }

    
}
