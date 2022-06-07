package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {
    @Id
    @Column(name = "CODIGO_ENDERECO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "endereco_gerator")
    @SequenceGenerator(name = "endereco_gerator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_ENDERECO")
    private Long codigoEndereco;
    //Caso delete a pessoa remova os enderecos cascade = CascadeType.REMOVE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="CODIGO_PESSOA")
    private Pessoa codigoPessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="CODIGO_BAIRRO")
    private Bairro codigoBairro;

    @Column(name = "NOME_RUA")
    private String nomeRua;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Column(name = "CEP")
    private String cep;

    public Endereco() {
    }

    public Endereco(Pessoa codigoPessoa, Bairro codigoBairro, String nomeRua, String numero, String complemento, String cep) {
        this.codigoPessoa = codigoPessoa;
        this.codigoBairro = codigoBairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco(Bairro codigoBairro, String nomeRua, String numero, String complemento, String cep) {
        this.codigoBairro = codigoBairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

}
