package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;

import java.util.List;

public interface BairroService {

    public BairroDTO salvar(BairroDTO bairroDTO);
    public BairroDTO atualizar(BairroDTO bairroDTO);
    List<BairroDTO> deletar(Long codigoBairro);

    List<BairroDTO> listarTodos();

    BairroDTO consultarPorCodigoBairro(Long codigoBairro);

    List<BairroDTO> listarPorCodigoMunicipio(Long codigoMunicipio);

    Object bairroCustomRepository(Long codigoBairro, Long codigoMunicipio, String nome, Integer status);
}
