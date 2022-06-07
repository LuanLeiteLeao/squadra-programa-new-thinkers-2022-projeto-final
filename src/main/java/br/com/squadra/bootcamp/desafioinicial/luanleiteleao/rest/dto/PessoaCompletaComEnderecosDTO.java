package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaCompletaComEnderecosDTO extends PessoaRessumidaSemEnderecosDTO{

    private List<EnderecoCompletoDTO> enderecos;

}
