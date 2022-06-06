package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity()
@Table(name = "TB_PESSOA")
public class Pessoa {
    @Id
    @Column(name = "CODIGO_PESSOA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pessoa_gerator")
    @SequenceGenerator(name = "pessoa_gerator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_PESSOA")
    private Long codigoPessoa;

    @Column(name = "NOME")
    private String nome;
    @Column(name = "SOBRENOME")
    private String sobrenome;
    @Column(name = "IDADE")
    private Integer idade;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "SENHA")
    private String senha;

    @Column(name = "STATUS")
    private Integer status;

    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, Integer idade, String login, String senha, Integer status) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }
}
