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

import com.voll.api.medico.DadosAtualizacaoMedico;
import com.voll.api.medico.DadosCadastroMedico;
import com.voll.api.medico.DadosListagemMedico;
import com.voll.api.medico.Medico;
import com.voll.api.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    /** Lista 1 todos tradicional não é boa prática
    @GetMapping 
    public List<Medico> listar() {
        return repository.findAll();
    }
    */

    /** Lista 2 Capitulo 4 video 2
    @GetMapping  // Capitulo 4 video 2
    public List<DadosListagemMedico> listar() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
    */

    /** Lista 3 Capitulo 4 video 3
    @GetMapping  // Capitulo 4 video 3
    public Page<DadosListagemMedico> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
    */
    
    /** Listar Todos  
    @GetMapping  // Lista 4 Capitulo 4 video 4
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
    */

    @GetMapping  // Lista 4 Capitulo 4 video 4
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }
    
    /** Paginações exemplos URLs
     *  Size   http://localhost:8080/medicos?size=1
     *  Page   http://localhost:8080/medicos?size=1&page=2
     *  Order  http://localhost:8080/medicos?sort=nome
     *  Order2 http://localhost:8080/medicos?sort=nome,desc
     *  Order3 http://localhost:8080/medicos?sort=nome,desc&size=2&page=0
     */

    @PutMapping
    @Transactional // Listagem condicionar se é true
    public void atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
     }

     /** Excluir Pernamente 
     @DeleteMapping("/{id}")
     @Transactional
     public void excluir(@PathVariable Long id){
        repository.deleteById(id);
     }
     */

     @DeleteMapping("/{id}") // Excluir condicionar
     @Transactional
     public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
     }
     
}
