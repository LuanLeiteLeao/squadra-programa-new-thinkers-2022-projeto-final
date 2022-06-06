package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Bairro;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Endereco;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Pessoa;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.PessoaRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.BairroDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.EnderecoDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.PessoaDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.PessoaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class PessoaServiceImp implements PessoaService {
    private PessoaRepository pessoaRepository;
    private BairroRepository bairroRepository;

    private EnderecoServiceImp enderecoService;

    public PessoaServiceImp(PessoaRepository pessoaRepository,BairroRepository bairroRepository,EnderecoServiceImp enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.bairroRepository = bairroRepository;
        this.enderecoService = enderecoService;
    }

    @Override
    @Transactional
    public PessoaDTO salvar(PessoaDTO pessoaDTO) {

        Pessoa pessoa = pessoaRepository.save(
                new Pessoa(
                        pessoaDTO.getNome(),
                        pessoaDTO.getSobrenome(),
                        pessoaDTO.getIdade(),
                        pessoaDTO.getLogin(),
                        pessoaDTO.getSenha(),
                        pessoaDTO.getStatus()

                )
        );
        List<EnderecoDTO> listaDeEndereco = enderecoService.salvarListaDeEnderecos(pessoaDTO.getEnderecos(),pessoa);

        PessoaDTO pessoaSalva = new PessoaDTO(
                pessoa.getCodigoPessoa(),
                pessoa.getNome(),
                pessoa.getSobrenome(),
                pessoa.getIdade(),
                pessoa.getLogin(),
                pessoa.getSenha(),
                listaDeEndereco,
                pessoa.getStatus()
        );

        return pessoaSalva;
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


}
