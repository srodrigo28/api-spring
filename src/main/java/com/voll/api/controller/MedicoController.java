package com.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.voll.api.medico.DadosAtualizacaoMedico;
import com.voll.api.medico.DadosCadastroMedico;
import com.voll.api.medico.DadosDetalhamentoMedico;
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
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
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
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
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
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
     }

     /** Excluir Pernamente 
        * @DeleteMapping("/{id}")
        * @Transactional
        * public void excluir(@PathVariable Long id){
        *    repository.deleteById(id);
        * }
     */

     @DeleteMapping("/{id}") // Excluir condicionar
     @Transactional
     public ResponseEntity<String> excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
     }
     
}
