package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MunicipioCustomRepository {

    private final EntityManager em;


    public MunicipioCustomRepository(EntityManager em) {
        this.em = em;
    }

    public Object find(Long codigoMunicipio, UF codigoUF, String nome, Integer status) {


        String query = "select mu from Municipio as mu ";
        String condicao = " where ";
        Boolean isPesquisaPorCodigoMunicipio = true;


        if(codigoUF != null) {
            query += condicao + " mu.codigoUF = :codigoUF";
            condicao = " and ";

            isPesquisaPorCodigoMunicipio=false;
        }
        if(codigoMunicipio != null) {
            query += condicao + " mu.codigoMunicipio = :codigoMunicipio";
            condicao = " and ";

        }

        if(nome != null) {
            query += condicao + " mu.nome = :nome";
            condicao = " and ";
            isPesquisaPorCodigoMunicipio=false;
        }

        if(status != null) {
            query += condicao + " mu.status = :status";
            isPesquisaPorCodigoMunicipio=false;
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
        if(isPesquisaPorCodigoMunicipio){

            return MunicipioDTO.converte(listaMunicipio.get(0));
        }

        return listaMunicipio.
               stream()
               .map(MunicipioDTO::converte)
               .collect(Collectors.toList());

    }

}