package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;


import javax.validation.constraints.NotNull;

public class UFDTOComId extends UFDTO{
    @NotNull(message = "Campo codigoUf é obrigatório")
    private Long codigoUF;

    public UFDTOComId(String sigla, String nome, Integer status) {
        super(sigla, nome, status);
    }

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
    }
}
