package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data@Entity
@Table(name = "TB_MUNICIPIO")
public class Municipio {
    @Id
    @Column(name = "CODIGO_MUNICIPIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "municipio_gerator")
    @SequenceGenerator(name = "municipio_gerator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_MUNICIPIO")
    private Long codigoMunicipio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="CODIGO_UF")
    private UF codigoUF;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public Municipio(UF uf, String nome, Integer status) {
        this.codigoUF = uf;
        this.nome = nome;
        this.status = status;
    }

    public Municipio(Long codigoMunicipio, UF codigoUF, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }

    public Municipio() {
        super();
    }
}
