package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;

import java.util.List;

public interface MunicipioService {
    List<MunicipioDTO> salvar(MunicipioDTO municipioDTO);

    List<MunicipioDTO> atualizar(MunicipioDTO municipioDTO);
     List<MunicipioDTO> deletar(Long codigoMunicipio);

    List<MunicipioDTO> listarTodos();

    MunicipioDTO consultandoPorcodigoMunicipio(Long codigoMunicipio);

    List<MunicipioDTO> listarPorCodigoUF(Long codigoUF);

    Object findPersonByCustom(Long codigoMunicipio, Long codigoUF, String nome, Integer status);
}
