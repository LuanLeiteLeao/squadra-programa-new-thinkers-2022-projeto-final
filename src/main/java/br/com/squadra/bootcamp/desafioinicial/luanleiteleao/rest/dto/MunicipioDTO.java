package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MunicipioDTO {

    private Long codigoMunicipio;
    @NotNull(message = "Campo codigoUF é obrigatório")
    private Long codigoUF;

    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message = "Campo nome não pode ser vazio")
    @Size(max = 256, message = "Campo nome tem que ter no máximo o tamanho de 256 caracteres")
    private String nome;

    @NotNull(message = "Campo status é obrigatório")
    private Integer status;

    public MunicipioDTO(Long uf, String nome, Integer status) {
        this.codigoUF = uf;
        this.nome = nome;
        this.status = status;
    }

    public MunicipioDTO() {

    }
    public MunicipioDTO(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }

    public static MunicipioDTO converte(Municipio municipio){
        return new MunicipioDTO(
                municipio.getCodigoMunicipio(),
                municipio.getCodigoUF().getCodigoUF(),
                municipio.getNome(),
                municipio.getStatus()
        );
    }
}
