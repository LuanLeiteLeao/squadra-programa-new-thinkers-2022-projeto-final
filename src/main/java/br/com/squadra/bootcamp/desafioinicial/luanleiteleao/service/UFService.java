package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTO;

import java.util.List;
import java.util.Optional;

public interface UFService {
    UF salvar(UFDTO ufDTO);
    List<UF> deletar(Long codigoUf);

    UF atualizar(UF uf);

    List<UF> ListarTodos();

    UF buscarPorUFSigla(String sigla);
    
    UF buscarPorUFcodigoUF(Long codigoUf);
}
