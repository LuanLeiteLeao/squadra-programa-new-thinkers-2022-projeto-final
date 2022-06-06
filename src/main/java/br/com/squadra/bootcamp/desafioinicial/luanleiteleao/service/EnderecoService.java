package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.EnderecoDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaDTO;

import java.util.List;

public interface EnderecoService {
    public List<EnderecoDTO> salvarListaDeEnderecos(List<EnderecoDTO> endereco, Pessoa pessoa);
}
