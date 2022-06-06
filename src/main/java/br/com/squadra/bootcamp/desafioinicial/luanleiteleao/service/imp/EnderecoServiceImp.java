package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.EnderecoRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.EnderecoDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.EnderecoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class EnderecoServiceImp implements EnderecoService {

    EnderecoRepository enderecoRepository;
    BairroRepository bairroRepository;

    public EnderecoServiceImp(EnderecoRepository enderecoRepository, BairroRepository bairroRepository) {
        this.enderecoRepository = enderecoRepository;
        this.bairroRepository = bairroRepository;
    }

    @Override
    @Transactional
    public List<EnderecoDTO> salvarListaDeEnderecos(List<EnderecoDTO> endereco, Pessoa pessoa) {
        List<Endereco> listaEnderecos = prepararEnderecoParaSalvar(endereco,pessoa);
        List<Endereco> enderecosSalvos = enderecoRepository.saveAll(listaEnderecos);
        return converterParaEnderecoDTO(enderecosSalvos);
    }

    private List<EnderecoDTO> converterParaEnderecoDTO(List<Endereco> listaDeEndereco){
        List<EnderecoDTO> listaConvertida = new ArrayList<>();

        listaDeEndereco.forEach(end -> {
            listaConvertida.add(new EnderecoDTO(
                    end.getCodigoEndereco(),
                    end.getCodigoPessoa().getCodigoPessoa(),
                    end.getCodigoBairro().getCodigoBairro(),
                    end.getNomeRua(),
                    end.getNumero(),
                    end.getComplemento(),
                    end.getCep()
            ));
        });

        return listaConvertida;
    }

    private List<Endereco> prepararEnderecoParaSalvar(List<EnderecoDTO> enderecos,Pessoa pessoa) {
        List<Endereco> enderecoSet = new ArrayList<>();
        enderecos.forEach(end -> {
            enderecoSet.add(
                    new Endereco(
                            pessoa,
                            getBairroOuLancaErro(end.getCodigoBairro()),
                            end.getNomeRua(),
                            end.getNumero(),
                            end.getComplemento(),
                            end.getCep()
                    )
            );
        });

        return enderecoSet;
    }

    private Bairro getBairroOuLancaErro(Long codigoBairro){
        return bairroRepository.findById(codigoBairro)
                .orElseThrow(()-> new ResponseStatusException(
                        BAD_REQUEST,"Não Existe nem um Bairro cadastrado com esse codigoBairro: "+codigoBairro));

    }
}
