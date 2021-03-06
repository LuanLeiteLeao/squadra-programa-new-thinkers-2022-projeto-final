package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidadoresGerais {

    static public void validaSeCampoForNulo(Object campo, String nomeDoCampo) {
        if (campo == null){
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    String.format(" Campo %s é obrigatório",
                            nomeDoCampo));
        }
    }

    static public void validaSeCampoStringForVazio(String campo, String nomeDoCampo) {
        if (campo.equals("")){
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    String.format(" Campo %s não pode ser vazio",
                            nomeDoCampo));
        }
    }

    static public void validaSeCampoListaForVazio(List campo, String nomeDoCampo){
        if (campo.isEmpty()){
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    String.format(" Lista %s não pode ser vazio",
                            nomeDoCampo));
        }
    }

    static public void validaSeListaVeioVaziaDobancoDeDados(List lista,String menssagemErro){
        if(lista.isEmpty()){
            throw new ResponseStatusException(BAD_REQUEST,menssagemErro);
        }
    }

    static public void validaSeEstatusTemEntradaValida(Integer status){
        if(status!=1 && status!=2){
            throw new ResponseStatusException(BAD_REQUEST,"O campo status só aceita 1 ou 2, (1-ativado, 2-desativado)");
        }
    }
}
