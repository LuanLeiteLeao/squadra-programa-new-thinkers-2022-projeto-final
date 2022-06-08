package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroCustomRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.MunicipioRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.BairroService;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.ValidadoresGerais;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.ValidadoresGerais.validaSeCampoForNulo;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BairroServiceImp implements BairroService {

    private BairroRepository bairroRepository;
    private MunicipioRepository municipioRepository;
    private BairroCustomRepository bairroCustomRepository;


    public BairroServiceImp(BairroRepository bairroRepository, MunicipioRepository municipioRepository, BairroCustomRepository bairroCustomRepository) {
        this.bairroRepository = bairroRepository;
        this.municipioRepository = municipioRepository;
        this.bairroCustomRepository = bairroCustomRepository;
    }

    @Override
    @Transactional
    public List<BairroDTO> salvar(BairroDTO bairroDTO) {
        Municipio municipio = getMunicipioOuLancaErro(bairroDTO.getCodigoMunicipio());
        ValidadoresGerais.validaSeEstatusTemEntradaValida(bairroDTO.getStatus());
        validaSeJaExisteBairroCadastradoComMesmoNomeEMunicipio(bairroDTO,municipio);

       bairroRepository.save(
                new Bairro(
                        bairroDTO.getCodigoBairro(),
                        municipio,
                        bairroDTO.getNome(),
                        bairroDTO.getStatus()
                )
        );

        return buscarTodosBairrosDTO();
    }

    private List<BairroDTO> buscarTodosBairrosDTO() {
        return bairroRepository
                .findAll()
                .stream()
                .map(BairroDTO::converte)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BairroDTO> atualizar(BairroDTO bairroDTO) {
        Municipio municipio = getMunicipioOuLancaErro(bairroDTO.getCodigoMunicipio());
        ValidadoresGerais.validaSeEstatusTemEntradaValida(bairroDTO.getStatus());
        ValidadoresGerais.validaSeCampoForNulo(bairroDTO.getCodigoBairro(),"codigoBairro");



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

        return buscarTodosBairrosDTO();
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

    @Override
    public Object bairroCustomRepository(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {
        Municipio codigoMunicipioValidado =null;
        boolean isRetornaListaVazia=false;

        if(codigoMunicipio!=null){
            try {
                codigoMunicipioValidado = municipioRepository.findById(codigoMunicipio).get();
            }catch (NoSuchElementException ex){
                isRetornaListaVazia=true;
            }
        }

        if(isRetornaListaVazia){
            return new ArrayList<>();
        }

        return bairroCustomRepository
                .find(codigoBairro,codigoMunicipioValidado,nome, status);
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

    private void validaSeJaExisteBairroCadastradoComMesmoNomeEMunicipio(BairroDTO bairroDTO, Municipio municipio) {
        List<MunicipioDTO> test = (List<MunicipioDTO>)bairroCustomRepository.find(null,
                municipio,
                bairroDTO.getNome(),
                null);

        if(test.size() > 0){
            throw new ResponseStatusException(BAD_REQUEST,"Não foi possível cadastrar Bairro no banco de dados, pois já existe um Bairro cadastrado com mesmo nome");
        }
    }

}
