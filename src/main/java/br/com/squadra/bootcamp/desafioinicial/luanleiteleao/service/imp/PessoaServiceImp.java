package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.PessoaRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.EnderecoDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.PessoaService;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.ValidadoresGerais;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static br.com.squadra.bootcamp.desafioinicial.luanleiteleao.validation.ValidadoresGerais.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class PessoaServiceImp implements PessoaService {
    private PessoaRepository pessoaRepository;
    private BairroRepository bairroRepository;

    private EnderecoServiceImp enderecoService;

    public PessoaServiceImp(PessoaRepository pessoaRepository, BairroRepository bairroRepository, EnderecoServiceImp enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.bairroRepository = bairroRepository;
        this.enderecoService = enderecoService;
    }

    @Override
    @Transactional
    public PessoaDTO salvar(PessoaDTO pessoaDTO) {
//        validacoes
        validaSeCampoForNulo(pessoaDTO.getEnderecos(),"enderecos");
        validaSeCampoListaForVazio(pessoaDTO.getEnderecos(),"enderecos");

        Pessoa pessoaSalvada = salvarPessoaDTO(pessoaDTO);
        List<EnderecoDTO> listaDeEndereco = enderecoService
                .salvarListaDeEnderecos(pessoaDTO.getEnderecos(), pessoaSalvada);

        return converteParaPessoaDTO(pessoaSalvada, listaDeEndereco);
    }

    @Override
    public PessoaDTO atualizar(PessoaDTO pessoaDTO) {
        //validadores
        validaSeCampoForNulo(pessoaDTO.getCodigoPessoa(),"codigoPessoa");
        validaSeCampoForNulo(pessoaDTO.getEnderecos(),"codigoPessoa");
        validaSeCampoListaForVazio(pessoaDTO.getEnderecos(),"enderecos");
        //Atualizar pessoa
        Pessoa pessoaAcahda = getPessoaOuLancaErro(pessoaDTO.getCodigoPessoa());
        Pessoa pessocaParaSerSalva = converterParaPessoa(pessoaDTO);
        pessocaParaSerSalva.setCodigoPessoa(pessoaAcahda.getCodigoPessoa());
        Pessoa pessoaAtualizada = pessoaRepository.save(pessocaParaSerSalva);
        //atualizar enderecos
        enderecoService.salvarListaDeEnderecos(pessoaDTO.getEnderecos(), pessoaAcahda);
        List<EnderecoDTO> enderecoAtualizado = enderecoService.
                listarEnderecosPorCodigoPessoa(pessoaAcahda);
        return converteParaPessoaDTO(pessoaAtualizada,enderecoAtualizado);
    }

    @Override
    public void delete(Long codigoPessoa) {
        Pessoa pessoa = getPessoaOuLancaErro(codigoPessoa);
        enderecoService.deletarTodosEnderecosPorPessoa(pessoa);
        pessoaRepository.delete(pessoa);
    }

    private Pessoa salvarPessoaDTO(PessoaDTO pessoaDTO) {
        return pessoaRepository.save(
                new Pessoa(
                        pessoaDTO.getNome(),
                        pessoaDTO.getSobrenome(),
                        pessoaDTO.getIdade(),
                        pessoaDTO.getLogin(),
                        pessoaDTO.getSenha(),
                        pessoaDTO.getStatus()

                )
        );
    }

    private Pessoa converterParaPessoa(PessoaDTO pessoaDTO) {
        return new Pessoa(
                pessoaDTO.getNome(),
                pessoaDTO.getSobrenome(),
                pessoaDTO.getIdade(),
                pessoaDTO.getLogin(),
                pessoaDTO.getSenha(),
                pessoaDTO.getStatus()

        );
    }

    private PessoaDTO converteParaPessoaDTO(Pessoa pessoaSalvada, List<EnderecoDTO> listaDeEndereco) {
        return new PessoaDTO(
                pessoaSalvada.getCodigoPessoa(),
                pessoaSalvada.getNome(),
                pessoaSalvada.getSobrenome(),
                pessoaSalvada.getIdade(),
                pessoaSalvada.getLogin(),
                pessoaSalvada.getSenha(),
                listaDeEndereco,
                pessoaSalvada.getStatus()
        );
    }

    private Pessoa getPessoaOuLancaErro(Long codigoPessoa){
        return pessoaRepository.findById(codigoPessoa)
                .orElseThrow(()-> new ResponseStatusException(
                        BAD_REQUEST,"Não Existe nem um Pessoa cadastrado com esse codigoPessoa: "+codigoPessoa));

    }

}
