package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFSRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.exception.JaExisteUmRegistroSalvoException;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.UFService;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UFServideImp implements UFService {
    private UFSRepository ufsRepository;

    public UFServideImp(UFSRepository ufsRepository) {
        this.ufsRepository = ufsRepository;
    }


    @Override
    @Transactional
    public UF salvar(UFDTO ufDTO) {
        validaSeExisteUFComSiglaCadastrado(ufDTO);

        return ufsRepository.save(
          new UF(ufDTO.getSigla(), ufDTO.getNome(), ufDTO.getStatus())
       );

    }

    @Override
    public List<UF> deletar(Long codigoUf) {
        if(codigoUf == null){
            throw new InvalidDataAccessApiUsageException("codigoUf não pode ser nulo");
        }

        ufsRepository.findById(codigoUf)
                .map(uf ->{
                    ufsRepository.delete(uf);
                    return true;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UF não encotrado"));


        return ufsRepository.findAll();
    }

    @Override
    public UF atualizar(UF uf) {
        return ufsRepository.findById(uf.getCodigoUf())
                .map(u-> ufsRepository.save(uf))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UF não encontrado"));
    }

    @Override
    public List<UF> ListarTodos() {
        return ufsRepository.findAll();
    }

    @Override
    public UF buscarPorUFSigla(String sigla) {
       return ufsRepository.procuraPorSiglaTodasUF(sigla).get(0);
    }

    @Override
    public UF buscarPorUFcodigoUF(Long codigoUf) {
        return ufsRepository.findById(codigoUf).map(
                uf->uf
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UF não encontrado"));
    }


    private void validaSeExisteUFComSiglaCadastrado(UFDTO ufDTO){
        if(ufsRepository.buscarQuantidadeDeNomeSalvas(ufDTO.getNome()) > 0){
            throw new  JaExisteUmRegistroSalvoException("nome",ufDTO.getNome());
        }
        else if(ufsRepository.buscarQuantidadeDeSiglaSalvas(ufDTO.getSigla()) > 0){
            throw new  JaExisteUmRegistroSalvoException("sigla", ufDTO.getSigla());
        }
    }
}
