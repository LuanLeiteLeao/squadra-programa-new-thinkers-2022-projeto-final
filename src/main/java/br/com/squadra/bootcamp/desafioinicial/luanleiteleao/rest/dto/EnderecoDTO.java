package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoDTO {
    private Long codigoEndereco;

    @NotNull(message = "Campo codigoPessoa é obrigatório")
    private Long codigoPessoa;

    @NotNull(message = "Campo codigoBairro é obrigatório")
    private Long codigoBairro;

    @NotNull(message = "Campo nomeRua é obrigatório")
    @NotEmpty(message = "Campo nomeRua não pode ser vazio")
    private String nomeRua;

    @NotNull(message = "Campo numero é obrigatório")
    @NotEmpty(message = "Campo numero não pode ser vazio")
    private String numero;

    @NotNull(message = "Campo complemento é obrigatório")
    @NotEmpty(message = "Campo complemento não pode ser vazio")
    private String complemento;

    @NotNull(message = "Campo cep é obrigatório")
    @NotEmpty(message = "Campo cep não pode ser vazio")
    private String cep;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long codigoEndereco, Long codigoPessoa, Long codigoBairro, String nomeRua, String numero, String complemento, String cep) {
        this.codigoEndereco = codigoEndereco;
        this.codigoPessoa = codigoPessoa;
        this.codigoBairro = codigoBairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public EnderecoDTO(Long codigoPessoa, Long codigoBairro, String nomeRua, String numero, String complemento, String cep) {
        this.codigoPessoa = codigoPessoa;
        this.codigoBairro = codigoBairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }
}
