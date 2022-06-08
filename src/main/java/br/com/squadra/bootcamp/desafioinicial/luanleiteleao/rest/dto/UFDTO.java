package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UFDTO {
    @NotNull(message = "Campo sigla é obrigatório")
    @NotEmpty(message = "Campo sigla não pode ser vazio")
    @Size(min = 2, message = "Campo sigla tem que ter no minimo o tamanho de 2 caracteres")
    @Size(max = 3, message = "Campo sigla tem que ter no máximo o tamanho de 3 caracteres")
    private  String sigla;
    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message ="Campo nome não pode ser vazio")
    @Size(max = 60, message = "Campo nome tem que ter no máximo o tamanho de 60 caracteres")
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
