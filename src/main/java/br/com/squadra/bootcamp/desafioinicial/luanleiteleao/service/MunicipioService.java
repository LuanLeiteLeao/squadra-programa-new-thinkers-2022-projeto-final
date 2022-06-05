package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;

import java.util.List;

public interface MunicipioService {
    MunicipioDTO salvar(MunicipioDTO municipioDTO);

    MunicipioDTO atualizar(MunicipioDTO municipioDTO);
    public List<MunicipioDTO> deletar(Long codigoMunicipio);
}
