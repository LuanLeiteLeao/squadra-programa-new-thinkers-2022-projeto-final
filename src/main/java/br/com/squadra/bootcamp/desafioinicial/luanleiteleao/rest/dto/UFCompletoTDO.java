package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UFCompletoTDO {
    private Long codigoUF;
    private  String sigla;
    private String nome;
    private Integer status;

    public UFCompletoTDO(Long codigoUF, String sigla, String nome, Integer status) {
        this.codigoUF = codigoUF;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}

