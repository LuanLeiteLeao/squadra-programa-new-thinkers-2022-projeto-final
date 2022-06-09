package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.EnderecoCompletoDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaCompletaComEnderecosDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.EnderecoServiceImp;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PessoaCustomRepository {

    private final EntityManager em;
    private EnderecoServiceImp enderecoService;
    private static String SELECT = "select pe from Pessoa as pe ";


    public PessoaCustomRepository(EntityManager em, EnderecoServiceImp enderecoService) {
        this.em = em;
        this.enderecoService = enderecoService;
    }

    public Object find(Long codigoPessoa, String login, Integer status) {


        String query = SELECT;
        String condicao = " where ";
        boolean isPesquisaPorPKOuFK=true;

        if(codigoPessoa != null) {
            query += condicao + " pe.codigoPessoa = :codigoPessoa";
            condicao = " and ";

        }
        if(login != null) {
            query += condicao + " pe.login = :login";
            condicao = " and ";
            isPesquisaPorPKOuFK=false;
        }

        if(status != null) {
            query += condicao + " pe.status = :status";
            condicao = " and ";
            isPesquisaPorPKOuFK=false;
        }

//        se todo mundo for nulo
        if(query.equals(SELECT)){
            isPesquisaPorPKOuFK=false;
        }

        var q = em.createQuery(query, Pessoa.class);

        if(codigoPessoa != null) {
            q.setParameter("codigoPessoa", codigoPessoa);
        }


        if(login != null) {
            q.setParameter("login", login);
        }


        if(status != null) {
            q.setParameter("status", status);
        }

        List<Pessoa> listaPessoas = q.getResultList();

        //retorna lista se nao estiver vazia
        if(listaPessoas.isEmpty()){
            return listaPessoas;
        }
        //retorna apenas uma
        if(isPesquisaPorPKOuFK){
            Pessoa pessoa = listaPessoas.get(0);
            List<EnderecoCompletoDTO> enderecos = enderecoService
                    .listarEnderecosPorCodigoPessoa(pessoa)
                    .stream()
                    .map(EnderecoCompletoDTO::converter).collect(Collectors.toList());

            return PessoaCompletaComEnderecosDTO.converte(pessoa,enderecos);
        }


        return listaPessoas.
               stream()
               .map(PessoaDTO::converte)
               .collect(Collectors.toList());

    }

}