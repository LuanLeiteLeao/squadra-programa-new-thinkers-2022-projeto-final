package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity;

import javax.persistence.*;
@Entity
@Table(name = "TB_UF")
public class UF {
    @Id
    @Column(name = "CODIGO_UF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "uf_gerator")
    @SequenceGenerator(name = "uf_gerator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_UF")
    private Long codigoUF;
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

    public UF(Long codigoUF, String sigla, String nome, Integer status) {
        this.codigoUF = codigoUF;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }


    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
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
                "codigoUf=" + codigoUF +
                ", sigla='" + sigla + '\'' +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                '}';
    }
}
