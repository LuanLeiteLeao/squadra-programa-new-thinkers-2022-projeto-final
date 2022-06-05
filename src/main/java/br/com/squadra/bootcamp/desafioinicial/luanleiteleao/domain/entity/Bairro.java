package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_BAIRRO")
public class Bairro {
    @Id
    @Column(name = "CODIGO_BAIRRO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bairro_gerator")
    @SequenceGenerator(name = "bairro_gerator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_BAIRRO")
    private Long codigoBairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private Municipio codigoMunicipio;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public Bairro() {
    }

    public Bairro(Municipio codigoMunicipio, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }

    public Bairro(Long codigoBairro, Municipio codigoMunicipio, String nome, Integer status) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }
}
