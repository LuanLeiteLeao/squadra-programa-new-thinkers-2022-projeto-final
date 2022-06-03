package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
//
//CREATE TABLE TB_UF
//        (
//        CODIGO_UF NUMBER(9) NOT NULL
//        , SIGLA VARCHAR2(3) NOT NULL
//        , NOME VARCHAR2(60) NOT NULL
//        , STATUS NUMBER(3) NOT NULL
//        , CONSTRAINT TB_UF_PK PRIMARY KEY
//        (
//        CODIGO_UF
//        )
//        ENABLE
//        );

@Entity
@Table(name = "TB_UF")
public class UF {
    @Id
    @Column(name = "CODIGO_UF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "uf_gerator")
    @SequenceGenerator(name = "uf_gerator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_UF")
    private Long  codigoUf;
    @Column(name = "SIGLA")
    private  String sigla;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public UF(){
        super();
    }
    public UF(String sigla, String nome, Integer status) {
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(Long codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UF{" +
                "codigoUf=" + codigoUf +
                ", sigla='" + sigla + '\'' +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                '}';
    }
}
