package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UFCustomRepository {

    private final EntityManager em;

    public UFCustomRepository(EntityManager em) {
        this.em = em;
    }

    public Object find(Long codigoUF,String sigla,String nome,Integer status) {
        String query = "select uf from UF as uf ";
        String condicao = " where ";
        Boolean isPesquisaPorStatus = true;


        if(codigoUF != null) {
            query += condicao + " uf.codigoUF = :codigoUF";
            condicao = " and ";

            isPesquisaPorStatus=false;
        }

        if(sigla != null) {
            query += condicao + " uf.sigla = :sigla";
            condicao = " and ";

            isPesquisaPorStatus=false;
        }

        if(nome != null) {
            query += condicao + " uf.nome = :nome";
            condicao = " and ";
            isPesquisaPorStatus=false;
        }

        if(status != null) {
            query += condicao + " uf.status = :status";
        }

        var q = em.createQuery(query, UF.class);

        if(codigoUF != null) {
            q.setParameter("codigoUF", codigoUF);
        }

        if(sigla != null) {
            q.setParameter("sigla", sigla);
        }

        if(nome != null) {
            q.setParameter("nome", nome);
        }

        if(status != null) {
            q.setParameter("status", status);
        }

        List<UF> listaDeUFS = q.getResultList();

        if(listaDeUFS.isEmpty()){
//            caso seja vazio retornar lista vazia
            return listaDeUFS;
        }
        if(isPesquisaPorStatus){
//            caso a pesquisa seja apenas de status deve retornar uma lista
//            ou caso seja todos
            return listaDeUFS;
        }
//        caso seja por outros paramentros retorna apenas um
        return listaDeUFS.get(0);



    }

}