package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UFDTO {
    @NotNull(message = "Campo sigla é obrigatório")
    @NotEmpty(message = "Campo sigla não pode ser vazio")
    private  String sigla;
    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message ="Campo nome não pode ser vazio")
    private String nome;

    @NotNull(message = "Campo status é obrigatório")
    private Integer status;

    public UFDTO(String sigla, String nome, Integer status) {
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }


    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
