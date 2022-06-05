package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BairroDTO {
    private Long codigoBairro;
    @NotNull(message = "Campo codigoMunicipio é obrigatório")
    private Long codigoMunicipio;

    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message = "Campo nome não pode ser vazio")
    private String nome;

    @NotNull(message = "Campo status é obrigatório")
    private Integer status;

    public BairroDTO(Long codigoMunicipio, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }

    public BairroDTO() {
    }

    public BairroDTO(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }
}
