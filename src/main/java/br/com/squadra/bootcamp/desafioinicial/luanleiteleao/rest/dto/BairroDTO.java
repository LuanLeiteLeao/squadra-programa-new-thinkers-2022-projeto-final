package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BairroDTO {
    private Long codigoBairro;
    @NotNull(message = "Campo codigoMunicipio é obrigatório")
    private Long codigoMunicipio;

    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message = "Campo nome não pode ser vazio")
    @Size(max = 256, message = "Campo nome tem que ter no máximo o tamanho de 256 caracteres")
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

    public static BairroDTO converte(Bairro bairro) {
        return new BairroDTO(
                bairro.getCodigoBairro(),
                bairro.getCodigoMunicipio().getCodigoMunicipio(),
                bairro.getNome(),
                bairro.getStatus());
    }
}
