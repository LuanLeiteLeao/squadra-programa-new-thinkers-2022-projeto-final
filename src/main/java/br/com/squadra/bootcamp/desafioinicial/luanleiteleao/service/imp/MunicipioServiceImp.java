package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.MunicipioCustomRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.MunicipioRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFSRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.MunicipioService;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import static org.springframework.http.HttpStatus.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioServiceImp implements MunicipioService {

    private UFSRepository ufsRepository;
    private MunicipioRepository municipioRepository;
    private MunicipioCustomRepository municipioCustomRepository;

    public MunicipioServiceImp(UFSRepository ufsRepository, MunicipioRepository municipioRepository, MunicipioCustomRepository municipioCustomRepository) {
        this.ufsRepository = ufsRepository;
        this.municipioRepository = municipioRepository;
        this.municipioCustomRepository = municipioCustomRepository;
    }

    @Override
    @Transactional
    public MunicipioDTO salvar(MunicipioDTO municipioDTO) {

        UF uf = getUfOuLancaErro(municipioDTO.getCodigoUF());

        Municipio municipioSalvo = municipioRepository.save(
                new Municipio(uf, municipioDTO.getNome(), municipioDTO.getStatus()));

        return converterParaMunicipioDTO(municipioSalvo);
    }



    @Override
    @Transactional
    public MunicipioDTO atualizar(MunicipioDTO municipioASerAtualizadoDTO) {

        UF ufASerAtualizado = getUfOuLancaErro(municipioASerAtualizadoDTO.getCodigoUF());
        Municipio municipioASerAtualizado = converterParaMunicipio(
                municipioASerAtualizadoDTO,
                ufASerAtualizado);

        Municipio municipioAtualizado = municipioRepository
                .findById(municipioASerAtualizadoDTO.getCodigoMunicipio())
                .map(municipio ->{
                            municipioASerAtualizado.setCodigoMunicipio(municipio.getCodigoMunicipio());
                            return municipioRepository.save(municipioASerAtualizado);
                        }
                    )
                .orElseThrow(() -> new ResponseStatusException(
                        BAD_REQUEST,
                        "Não Existe nem um Municipio cadastrado com esse codigoMunicipio: "
                                + municipioASerAtualizadoDTO.getCodigoMunicipio()));

        return converterParaMunicipioDTO(municipioAtualizado);

    }

    public List<MunicipioDTO> deletar(Long codigoMunicipio) {
        if(codigoMunicipio == null){
            throw new InvalidDataAccessApiUsageException("codigoMunicipio não pode ser nulo");
        }

        municipioRepository.findById(codigoMunicipio)
                .map(municipio ->{
                    municipioRepository.delete(municipio);
                    return true;
                })
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"codigoMunicipio não encotrado"));


        return municipioRepository.findAll()
                .stream()
                .map(m ->converterParaMunicipioDTO(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<MunicipioDTO> listarTodos() {
        return municipioRepository
                .findAll()
                .stream()
                .map(m ->converterParaMunicipioDTO(m))
                .collect(Collectors.toList());
    }

    @Override
    public MunicipioDTO consultandoPorcodigoMunicipio(Long codigoMunicipio) {
        Municipio municipioSalvo = municipioRepository
                .findById(codigoMunicipio)
                .orElseThrow(() ->
                        new ResponseStatusException(BAD_REQUEST,
                                "Não existe nem um Municipio cadastrado com este nome"));

        return converterParaMunicipioDTO(municipioSalvo);
    }

    @Override
    public List<MunicipioDTO> listarPorCodigoUF(Long codigoUF) {

        UF uf = getUfOuLancaErro(codigoUF);

        return municipioRepository
                .procuraPorcodigoUFTodosMunicipios(uf)
                .stream()
                .map(m ->converterParaMunicipioDTO(m))
                .collect(Collectors.toList());
    }

    @Override
    public Object findPersonByCustom(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        UF codigoUFValidado = null;

        if(codigoUF!=null){
           codigoUFValidado = getUfOuLancaErro(codigoUF);
        }

        return municipioCustomRepository
                .find(codigoMunicipio, codigoUFValidado, nome, status);


    }

    private Municipio converterParaMunicipio(MunicipioDTO municipioASerAtualizadoDTO, UF ufASerAtualizado) {
        return new Municipio(
                ufASerAtualizado,
                municipioASerAtualizadoDTO.getNome(),
                municipioASerAtualizadoDTO.getStatus()
        );
    }

    private MunicipioDTO converterParaMunicipioDTO(Municipio municipioSalvo) {
        return new MunicipioDTO(
                municipioSalvo.getCodigoMunicipio(),
                municipioSalvo.getCodigoUF().getCodigoUF(),
                municipioSalvo.getNome(),
                municipioSalvo.getStatus()
        );
    }

    private UF getUfOuLancaErro(Long codigoUf){
        return ufsRepository.findById(codigoUf)
                .orElseThrow(()-> new ResponseStatusException(
                        BAD_REQUEST,"Não Existe nem um estado cadastrado com esse codigoUf: "+codigoUf));

    }

}
