package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaDTO;

public interface PessoaService{
    PessoaDTO salvar(PessoaDTO pessoa);
}
