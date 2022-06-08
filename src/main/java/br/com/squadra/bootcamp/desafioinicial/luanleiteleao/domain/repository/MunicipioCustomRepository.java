package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MunicipioCustomRepository {

    private final EntityManager em;
    private static String SELECT = "select mu from Municipio as mu ";

    public MunicipioCustomRepository(EntityManager em) {
        this.em = em;
    }

    public Object find(Long codigoMunicipio, UF codigoUF, String nome, Integer status) {


        String query = SELECT;
        String condicao = " where ";
        Boolean isPesquisaPorPKOuFK = true;
        boolean isEntrouEmcodigoUF=false;
        boolean isEntrouEmcodigoMunicipio=false;


        if(codigoUF != null) {
            query += condicao + " mu.codigoUF = :codigoUF";
            condicao = " and ";
            isEntrouEmcodigoUF=true;

        }
        if(codigoMunicipio != null) {
            query += condicao + " mu.codigoMunicipio = :codigoMunicipio";
            condicao = " and ";
            isEntrouEmcodigoMunicipio=true;
        }

        if(nome != null) {
            query += condicao + " mu.nome = :nome";
            condicao = " and ";
            isPesquisaPorPKOuFK=false;
        }

        if(status != null) {
            query += condicao + " mu.status = :status";
            isPesquisaPorPKOuFK=false;
        }
//        se todo mundo for nulo
        if(query.equals(SELECT)){
            isPesquisaPorPKOuFK=false;
        }

        var q = em.createQuery(query, Municipio.class);

        if(codigoMunicipio != null) {
            q.setParameter("codigoMunicipio", codigoMunicipio);
        }


        if(codigoUF != null) {
            q.setParameter("codigoUF", codigoUF);
        }


        if(nome != null) {
            q.setParameter("nome", nome);
        }

        if(status != null) {
            q.setParameter("status", status);
        }

        List<Municipio> listaMunicipio = q.getResultList();

        //retorna lista se nao estiver vazia
        if(listaMunicipio.isEmpty()){
            return listaMunicipio;
        }
        //retorna apenas uma
        if(isPesquisaPorPKOuFK && isEntrouEmcodigoUF && isEntrouEmcodigoMunicipio ||
                (isPesquisaPorPKOuFK && isEntrouEmcodigoMunicipio )){
            return MunicipioDTO.converte(listaMunicipio.get(0));
        }

        return listaMunicipio.
               stream()
               .map(MunicipioDTO::converte)
               .collect(Collectors.toList());

    }

}