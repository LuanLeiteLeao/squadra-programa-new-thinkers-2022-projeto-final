package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class EnderecoCompletoDTO {
    private Long codigoEndereco;
    private Long codigoBairro;
    private Long codigoPessoa;
    private String nomeRua;
    private String numero;
    private String complemento;
    private String cep;
    private BairroCompletoDTO bairro;

    public EnderecoCompletoDTO(Long codigoEndereco, Long codigoBairro, Long codigoPessoa, String nomeRua, String numero, String complemento, String cep, BairroCompletoDTO bairro) {
        this.codigoEndereco = codigoEndereco;
        this.codigoBairro = codigoBairro;
        this.codigoPessoa = codigoPessoa;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
    }


    public static EnderecoCompletoDTO converter(Endereco endereco) {
        return new EnderecoCompletoDTO(
                endereco.getCodigoEndereco(),
                endereco.getCodigoBairro().getCodigoBairro(),
                endereco.getCodigoPessoa().getCodigoPessoa(),
                endereco.getNomeRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                BairroCompletoDTO.converter( endereco.getCodigoBairro()));
    }
}
