package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFSRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.exception.JaExisteUmRegistroSalvoException;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTOComId;
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
        validaSeExisteUFComSiglaJaCadastrado(ufDTO);

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
    @Transactional
    public UF atualizar(UFDTOComId uf) {
        validaSeExisteApenasUmaUFComSiglaOuNomeJaCadastrado(uf);

        return ufsRepository.findById(uf.getCodigoUF())
                .map(u-> ufsRepository.save(new UF(u.getCodigoUf(),
                        uf.getSigla(),
                        uf.getNome(),
                        uf.getStatus())))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UF não encontrado, codigoUf inválido"));
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


    private void validaSeExisteUFComSiglaJaCadastrado(UFDTO ufDTO){
        if(ufsRepository.buscarQuantidadeDeNomeSalvas(ufDTO.getNome()) > 0){
            throw new  JaExisteUmRegistroSalvoException("nome",ufDTO.getNome());
        }
        else if(ufsRepository.buscarQuantidadeDeSiglaSalvas(ufDTO.getSigla()) > 0){
            throw new  JaExisteUmRegistroSalvoException("sigla", ufDTO.getSigla());
        }
    }

    private void validaSeExisteApenasUmaUFComSiglaOuNomeJaCadastrado(UFDTO ufDTO){
        if(ufsRepository.buscarQuantidadeDeNomeSalvas(ufDTO.getNome()) >=2 ){
            throw new  JaExisteUmRegistroSalvoException("nome",ufDTO.getNome());
        }

        else if(ufsRepository.buscarQuantidadeDeSiglaSalvas(ufDTO.getSigla()) >=2 ){
            throw new  JaExisteUmRegistroSalvoException("sigla", ufDTO.getSigla());
        }
    }
}
