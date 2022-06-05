package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.MunicipioRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.BairroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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
        return null;
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
