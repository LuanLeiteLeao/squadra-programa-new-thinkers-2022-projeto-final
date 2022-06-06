package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro,Long> {
    @Query(" select ba from Bairro ba where ba.codigoMunicipio = :codigoMunicipio")
    List<Bairro> procuraPorCodigoMunicipioTodosBairros(@Param("codigoMunicipio") Municipio municipio);
}
