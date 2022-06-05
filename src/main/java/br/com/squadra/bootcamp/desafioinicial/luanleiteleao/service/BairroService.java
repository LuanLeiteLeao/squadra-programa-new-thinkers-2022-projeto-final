package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;

public interface BairroService {

    public BairroDTO salvar(BairroDTO bairroDTO);
    public BairroDTO atualizar(BairroDTO bairroDTO);

}
