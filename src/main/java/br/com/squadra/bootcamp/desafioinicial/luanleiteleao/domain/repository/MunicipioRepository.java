package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio,Long> {
    @Query(" select mu from Municipio mu where mu.codigoUF = :codigoUF")
    List<Municipio> procuraPorcodigoUFTodosMunicipios(@Param("codigoUF") UF uf);
}
