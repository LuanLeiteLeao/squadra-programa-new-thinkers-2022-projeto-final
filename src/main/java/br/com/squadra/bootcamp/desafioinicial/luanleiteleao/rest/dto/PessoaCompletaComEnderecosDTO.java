package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaCompletaComEnderecosDTO extends PessoaRessumidaSemEnderecosDTO{

    private List<EnderecoCompletoDTO> enderecos;

    public PessoaCompletaComEnderecosDTO() {

    }
    public PessoaCompletaComEnderecosDTO(Long codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, Integer status, List<EnderecoCompletoDTO> enderecos) {
        super(codigoPessoa, nome, sobrenome, idade, login, senha, status);
        this.enderecos = enderecos;
    }


    public static PessoaCompletaComEnderecosDTO converte(Pessoa pessoa, List<EnderecoCompletoDTO> enderecos) {
        return  new PessoaCompletaComEnderecosDTO(
                pessoa.getCodigoPessoa(),
                pessoa.getNome(),
                pessoa.getSobrenome(),
                pessoa.getIdade(),
                pessoa.getLogin(),
                pessoa.getSenha(),
                pessoa.getStatus(),
                enderecos
        );
    }
}
