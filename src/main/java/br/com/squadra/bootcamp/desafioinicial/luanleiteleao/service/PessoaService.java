package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaCompletaComEnderecosDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaRessumidaSemEnderecosDTO;

import java.util.List;

public interface PessoaService{
    PessoaDTO salvar(PessoaDTO pessoa);

    PessoaDTO atualizar(PessoaDTO pessoa);

    void delete(Long codigoPessoa);

    List<PessoaRessumidaSemEnderecosDTO> listarTodos();

    PessoaCompletaComEnderecosDTO pesquisarPessoaPorCodigoPessoa(Long codigoPessoa);

    Object findPersonByCustom(Long codigoPessoa, String login, Integer status);
}
