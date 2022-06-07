package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.MunicipioRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.BairroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.ValidadoresGerais.validaSeCampoForNulo;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BairroServiceImp implements BairroService {

    private BairroRepository bairroRepository;
    private MunicipioRepository municipioRepository;

    public BairroServiceImp(BairroRepository bairroRepository, MunicipioRepository municipioRepository) {
        this.bairroRepository = bairroRepository;
        this.municipioRepository = municipioRepository;
    }

    @Override
    @Transactional
    public BairroDTO salvar(BairroDTO bairroDTO) {
        Municipio municipio = getMunicipioOuLancaErro(bairroDTO.getCodigoMunicipio());

        Bairro bairroSalvo = bairroRepository.save(
                new Bairro(
                        bairroDTO.getCodigoBairro(),
                        municipio,
                        bairroDTO.getNome(),
                        bairroDTO.getStatus()
                )
        );

        return converterParaBairroDTO(bairroSalvo);
    }

    @Override
    @Transactional
    public BairroDTO atualizar(BairroDTO bairroDTO) {

        validaSeCampoForNulo(bairroDTO.getCodigoBairro(),"codigoBairro");

        Municipio municipio = getMunicipioOuLancaErro(bairroDTO.getCodigoMunicipio());
        Bairro bairroASerAtualizado = converterParaBairro(
                bairroDTO,
                municipio);

        Bairro bairroAtualizado = bairroRepository
                .findById(bairroASerAtualizado.getCodigoBairro())
                .map(bairro ->{
                            bairroASerAtualizado.setCodigoBairro(bairro.getCodigoBairro());
                            return bairroRepository.save(bairroASerAtualizado);
                        }
                )
                .orElseThrow(() -> new ResponseStatusException(
                        BAD_REQUEST,
                        "Não Existe nem um Bairro cadastrado com esse codigoBairro: "
                                + bairroASerAtualizado.getCodigoBairro()));

        return converterParaBairroDTO(bairroAtualizado);
    }

    @Override
    public List<BairroDTO> deletar(Long codigoBairro) {

        validaSeCampoForNulo(codigoBairro,"codigoBairro");

       bairroRepository.findById(codigoBairro)
                .map(bairro ->{
                    bairroRepository.delete(bairro);
                    return true;
                })
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"codigoBairro não encotrado"));

        return bairroRepository.findAll()
                .stream()
                .map(bairro ->converterParaBairroDTO(bairro))
                .collect(Collectors.toList());
    }

    @Override
    public List<BairroDTO> listarTodos() {
        return bairroRepository
                .findAll()
                .stream()
                .map(bairro -> converterParaBairroDTO(bairro))
                .collect(Collectors.toList());
    }

    @Override
    public BairroDTO consultarPorCodigoBairro(Long codigoBairro) {
        validaSeCampoForNulo(codigoBairro,"codigoBairro");
        Bairro bairroAchado = bairroRepository
                .findById(codigoBairro)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST,
                        "Não Existe nem um Bairro cadastrado com esse codigoBairro: " + codigoBairro));
        return converterParaBairroDTO(bairroAchado);
    }

    @Override
    public List<BairroDTO> listarPorCodigoMunicipio(Long codigoMunicipio) {
        Municipio municipio = getMunicipioOuLancaErro(codigoMunicipio);

        return bairroRepository
                .procuraPorCodigoMunicipioTodosBairros(municipio)
                .stream()
                .map(bairro -> converterParaBairroDTO(bairro))
                .collect(Collectors.toList());

    }

    private Bairro converterParaBairro(BairroDTO bairroDTO, Municipio municipio) {
       return new Bairro(
                bairroDTO.getCodigoBairro(),
                municipio,
                bairroDTO.getNome(),
                bairroDTO.getStatus()
        );
    }

    private Municipio getMunicipioOuLancaErro(Long codigoMunicipio) {
        return municipioRepository.findById(codigoMunicipio)
                .orElseThrow(()-> new ResponseStatusException(
                        BAD_REQUEST,"Não Existe nem um Municipio cadastrado com esse codigoMunicipio: "+codigoMunicipio));
    }

    private BairroDTO converterParaBairroDTO(Bairro bairro) {
        return new BairroDTO(
                bairro.getCodigoBairro(),
                bairro.getCodigoMunicipio().getCodigoMunicipio(),
                bairro.getNome(),
                bairro.getStatus()
        );
    }

}
