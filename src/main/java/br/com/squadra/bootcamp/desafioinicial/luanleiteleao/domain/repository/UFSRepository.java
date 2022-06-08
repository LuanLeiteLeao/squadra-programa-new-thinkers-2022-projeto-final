package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UFSRepository extends JpaRepository<UF, Long> {
    @Query(" select uf from UF uf where uf.sigla = :sigla")
    List<UF> procuraPorSiglaTodasUF(@Param("sigla") String sigla);

    @Query(" select uf from UF uf where uf.nome = :nome")
    List<UF> procuraPorNomeTodasUF(@Param("nome") String nome);

    @Query(" select uf from UF uf where uf.status = :status")
    List<UF> procuraPorStatusTodasUF(@Param("status") Integer status);

    @Query(" SELECT count(*) FROM UF uf WHERE sigla = :sigla ")
    Integer buscarQuantidadeDeSiglaSalvas(@Param("sigla") String sigla);

    @Query(" SELECT count(*) FROM UF uf WHERE nome = :nome ")
    Integer buscarQuantidadeDeNomeSalvas(@Param("nome") String nome);
}
