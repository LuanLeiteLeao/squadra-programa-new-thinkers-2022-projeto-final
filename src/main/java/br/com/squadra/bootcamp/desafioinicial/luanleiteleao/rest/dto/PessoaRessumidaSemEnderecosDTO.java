package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaRessumidaSemEnderecosDTO {

    private Long codigoPessoa;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String login;
    private String senha;
    private Integer status;

    public PessoaRessumidaSemEnderecosDTO() {
    }

    public PessoaRessumidaSemEnderecosDTO(Long codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, Integer status) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }
}
