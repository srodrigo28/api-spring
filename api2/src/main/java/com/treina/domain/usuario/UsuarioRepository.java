package com.treina.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.treina.models.usuario.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Page<UsuarioModel> findAllByAtivoTrue(Pageable paginacao);
}
