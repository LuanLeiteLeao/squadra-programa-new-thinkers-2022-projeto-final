package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioCompletoDTO {
    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    private Integer status;
    private UFCompletoTDO uf;

    public MunicipioCompletoDTO(Long codigoMunicipio, Long codigoUF, String nome, Integer status, UFCompletoTDO uf) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
        this.uf = uf;
    }
}

//"codigoMunicipio": 1,
//                        "codigoUF": 1,
//                        "nome": "JUNDIAÍ",
//                        "status": 1,
//                        "uf"
