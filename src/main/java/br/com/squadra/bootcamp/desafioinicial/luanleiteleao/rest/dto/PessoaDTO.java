package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.NotEmptyList;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PessoaDTO {

    private Long codigoPessoa;

    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message = "Campo nome não pode ser vazio")
    private String nome;

    @NotNull(message = "Campo sobrenome é obrigatório")
    @NotEmpty(message = "Campo sobrenome não pode ser vazio")
    private String sobrenome;

    @NotNull(message = "Campo idade é obrigatório")
    private Integer idade;

    @NotNull(message = "Campo login é obrigatório")
    @NotEmpty(message = "Campo login não pode ser vazio")
    private String login;

    @NotNull(message = "Campo senha é obrigatório")
    @NotEmpty(message = "Campo senha não pode ser vazio")
    private String senha;

//    @NotEmptyList(message = "campo de endereçoes não pode ser vazio")
    private List<EnderecoDTO> enderecos;

    @NotNull(message = "Campo status é obrigatório")
    private Integer status;

    public PessoaDTO() {
    }

    public PessoaDTO(Long codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, List<EnderecoDTO> enderecos, Integer status) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.enderecos = enderecos;
        this.status = status;
    }
}
