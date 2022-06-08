package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFSRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.exception.JaExisteUmRegistroSalvoException;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTOComId;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.UFService;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.ValidadoresGerais;
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
    public List<UF> salvar(UFDTO ufDTO) {
        validaSeExisteUFComSiglaJaCadastrado(ufDTO);
        ValidadoresGerais.validaSeEstatusTemEntradaValida(ufDTO.getStatus());

        ufsRepository.save(
          new UF(ufDTO.getSigla(), ufDTO.getNome(), ufDTO.getStatus())
       );

        return ufsRepository.findAll();
    }

    @Override
    public List<UF> deletar(Long codigoUf) {
        ValidadoresGerais.validaSeCampoForNulo(codigoUf,"codigoUF");

        ufsRepository.findById(codigoUf)
                .map(uf ->{
                    ufsRepository.delete(uf);
                    return true;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não foi possível consultar UF no banco de dados."));


        return ufsRepository.findAll();
    }

    @Override
    @Transactional
    public List<UF> atualizar(UFDTOComId uf) {
        validaSeExisteApenasUmaUFComSiglaOuNomeJaCadastrado(uf);
        ValidadoresGerais.validaSeEstatusTemEntradaValida(uf.getStatus());

        ufsRepository.findById(uf.getCodigoUF())
                .map(u-> ufsRepository.save(new UF(
                        uf.getCodigoUF(),
                        uf.getSigla(),
                        uf.getNome(),
                        uf.getStatus())))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não foi possível consultar UF no banco de dados."));

        return ufsRepository.findAll();
    }

    @Override
    public List<UF> ListarTodos() {
        return ufsRepository.findAll();
    }

    @Override
    public UF buscarPorUFSigla(String sigla) {
        List<UF> lista = ufsRepository.procuraPorSiglaTodasUF(sigla);
        ValidadoresGerais.validaSeListaVeioVaziaDobancoDeDados(lista,"Não foi possível consultar UF no banco de dados.");
        return lista.get(0);

    }

    @Override
    public UF buscarPorUFcodigoUF(Long codigoUf) {
        return ufsRepository.findById(codigoUf)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não foi possível consultar UF no banco de dados."));
    }

    @Override
    public UF buscarPorUFNome(String nome) {
        List<UF> lista = ufsRepository.procuraPorNomeTodasUF(nome);
        ValidadoresGerais.validaSeListaVeioVaziaDobancoDeDados(lista,"Não foi possível consultar UF no banco de dados.");
        return lista.get(0);
    }

    @Override
    public List<UF> buscarPorUFStatus(Integer status) {
        return ufsRepository.procuraPorStatusTodasUF(status);
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
        if(ufsRepository.buscarQuantidadeDeNomeSalvas(ufDTO.getNome()) >=1 ){
            throw new  JaExisteUmRegistroSalvoException("nome",ufDTO.getNome());
        }

        else if(ufsRepository.buscarQuantidadeDeSiglaSalvas(ufDTO.getSigla()) >=1 ){
            throw new  JaExisteUmRegistroSalvoException("sigla", ufDTO.getSigla());
        }
    }
}
