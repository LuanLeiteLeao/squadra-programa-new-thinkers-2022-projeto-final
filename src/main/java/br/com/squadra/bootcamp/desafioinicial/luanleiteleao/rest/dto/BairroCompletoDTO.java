package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BairroCompletoDTO {
    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    private Integer status;
    private MunicipioCompletoDTO municipio;

    public BairroCompletoDTO(Long codigoBairro, Long codigoMunicipio, String nome, Integer status, MunicipioCompletoDTO municipio) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
        this.municipio = municipio;
    }

    public static BairroCompletoDTO converter(Bairro codigoBairro) {
        return new BairroCompletoDTO(
                codigoBairro.getCodigoBairro(),
                codigoBairro.getCodigoMunicipio().getCodigoMunicipio(),
                codigoBairro.getNome(),
                codigoBairro.getStatus(),
                MunicipioCompletoDTO.converte( codigoBairro.getCodigoMunicipio())
        );
    }
}

