package com.voll.api.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private int quantidade;
    private float valor;

    public Produto(DadosCadastrarProduto dados){
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.quantidade = dados.quantidade();
        this.valor = dados.valor();
    }

    public void atualizarProduto(DadosAtualizarProduto dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.quantidade() != null){
            this.quantidade = dados.quantidade();
        }
        if(dados.valor() != null){
            this.valor = dados.valor();
        }
    }
}
