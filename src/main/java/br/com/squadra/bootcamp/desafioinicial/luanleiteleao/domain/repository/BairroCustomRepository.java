package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BairroCustomRepository {

    private final EntityManager em;
    private static String SELECT = "select ba from Bairro as ba ";

    public BairroCustomRepository(EntityManager em) {
        this.em = em;
    }

    public Object find(Long codigoBairro, Municipio codigoMunicipio, String nome, Integer status) {


        String query = SELECT;
        String condicao = " where ";
        Boolean isPesquisaPorPKOuFK = true;
        boolean isEntrouEmcodigoBairro=false;
        boolean isEntrouEmcodigoMunicipio=false;

        if(codigoBairro != null) {
            query += condicao + " ba.codigoBairro = :codigoBairro";
            condicao = " and ";
            isEntrouEmcodigoBairro = true;
      
        }
        if(codigoMunicipio != null) {
            query += condicao + " ba.codigoMunicipio = :codigoMunicipio";
            condicao = " and ";
            isEntrouEmcodigoMunicipio=true;
        }

        if(nome != null) {
            query += condicao + " ba.nome = :nome";
            condicao = " and ";
            isPesquisaPorPKOuFK=false;
        }

        if(status != null) {
            query += condicao + " ba.status = :status";
            isPesquisaPorPKOuFK=false;
        }
//        se todo mundo for nulo
        if(query.equals(SELECT)){
            isPesquisaPorPKOuFK=false;
        }

        var q = em.createQuery(query, Bairro.class);

        if(codigoBairro != null) {
            q.setParameter("codigoBairro", codigoBairro);
        }


        if(codigoMunicipio != null) {
            q.setParameter("codigoMunicipio", codigoMunicipio);
        }

        if(nome != null) {
            q.setParameter("nome", nome);
        }

        if(status != null) {
            q.setParameter("status", status);
        }

        List<Bairro> listaBairro = q.getResultList();

        //retorna lista se nao estiver vazia
        if(listaBairro.isEmpty()){
            return listaBairro;
        }
        //retorna apenas uma
        if(isPesquisaPorPKOuFK && isEntrouEmcodigoBairro && isEntrouEmcodigoMunicipio ||
                (isPesquisaPorPKOuFK && isEntrouEmcodigoBairro )){
            return BairroDTO.converte(listaBairro.get(0));
        }


        return listaBairro.
               stream()
               .map(BairroDTO::converte)
               .collect(Collectors.toList());

    }

}