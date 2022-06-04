package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.MunicipioRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFSRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.MunicipioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MunicipioServiceImp implements MunicipioService {

    private UFSRepository ufsRepository;
    private MunicipioRepository municipioRepository;

    public MunicipioServiceImp(UFSRepository ufsRepository, MunicipioRepository municipioRepository) {
        this.ufsRepository = ufsRepository;
        this.municipioRepository = municipioRepository;
    }

    @Override
    @Transactional
    public MunicipioDTO salvar(MunicipioDTO municipioDTO) {

        UF uf = getUfOuLancaErro(municipioDTO.getCodigoUF());

        Municipio municipioSalvo = municipioRepository.save(
                new Municipio(uf, municipioDTO.getNome(), municipioDTO.getStatus()));

        return new MunicipioDTO(
                municipioSalvo.getCodigoMunicipio(),
                municipioSalvo.getCodigoUf().getCodigoUf(),
                municipioSalvo.getNome(),
                municipioSalvo.getStatus()
                );

    }

    private UF getUfOuLancaErro(Long codigoUf){
        return ufsRepository.findById(codigoUf)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,"Não Existe nem um estado cadastrado com esse codigoUf: "+codigoUf));

    }
}
