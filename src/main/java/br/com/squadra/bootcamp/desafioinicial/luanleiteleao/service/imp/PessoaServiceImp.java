package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.*;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.BairroRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.PessoaRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.*;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.PessoaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        //Atualizar pessoa
        Pessoa pessoaAcahda = getPessoaOuLancaErro(pessoaDTO.getCodigoPessoa());
        Pessoa pessocaParaSerSalva = converterParaPessoa(pessoaDTO);
        pessocaParaSerSalva.setCodigoPessoa(pessoaAcahda.getCodigoPessoa());
        Pessoa pessoaAtualizada = pessoaRepository.save(pessocaParaSerSalva);
        //atualizar enderecos somente se ela n for vazia ou nula
        if(pessoaDTO.getEnderecos() !=null && !pessoaDTO.getEnderecos().isEmpty()) {
            enderecoService.salvarListaDeEnderecos(pessoaDTO.getEnderecos(), pessoaAcahda);
        }
        List<EnderecoDTO> enderecoAtualizado = enderecoService.listarEnderecosDTOPorCodigoPessoa(pessoaAcahda);
        return converteParaPessoaDTO(pessoaAtualizada,enderecoAtualizado);
    }

    @Override
    public void delete(Long codigoPessoa) {
        Pessoa pessoa = getPessoaOuLancaErro(codigoPessoa);
        enderecoService.deletarTodosEnderecosPorPessoa(pessoa);
        pessoaRepository.delete(pessoa);
    }

    @Override
    public List<PessoaRessumidaSemEnderecosDTO> listarTodos() {
        return pessoaRepository
                .findAll()
                .stream()
                .map(p ->
                        new PessoaRessumidaSemEnderecosDTO(
                                p.getCodigoPessoa(),
                                p.getNome(),
                                p.getSobrenome(),
                                p.getIdade(),
                                p.getLogin(),
                                p.getSenha(),
                                p.getStatus()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public PessoaCompletaComEnderecosDTO pesquisarPessoaPorCodigoPessoa(Long codigoPessoa) {
        Pessoa pessoa = getPessoaOuLancaErro(codigoPessoa);
        List<Endereco> enderecos = enderecoService.listarEnderecosPorCodigoPessoa(pessoa);

        PessoaCompletaComEnderecosDTO pessoaCompleta = convertePessoaParaPessoaCompletaComEnderecosDTO(pessoa);

        List<EnderecoCompletoDTO> enderecosCompleto = converterEnderecoParaEnderecoCompletoDTO(enderecos);

        pessoaCompleta.setEnderecos(enderecosCompleto);

        return pessoaCompleta;
    }

    private List<EnderecoCompletoDTO> converterEnderecoParaEnderecoCompletoDTO(List<Endereco> enderecos) {
        List<EnderecoCompletoDTO> enderecosCompleto = new ArrayList<>();

        enderecos.forEach(endereco -> {
            enderecosCompleto.add(
                    new EnderecoCompletoDTO(
                            endereco.getCodigoEndereco(),
                            endereco.getCodigoBairro().getCodigoBairro(),
                            endereco.getCodigoPessoa().getCodigoPessoa(),
                            endereco.getNomeRua(),
                            endereco.getNumero(),
                            endereco.getComplemento(),
                            endereco.getCep(),
                            converteBairroParaBairroCompletoDTO(endereco.getCodigoBairro())
                    )
            );
        });
        return enderecosCompleto;
    }

    private BairroCompletoDTO converteBairroParaBairroCompletoDTO(Bairro bairro) {
        return new BairroCompletoDTO(
                bairro.getCodigoBairro(),
                bairro.getCodigoMunicipio().getCodigoMunicipio(),
                bairro.getNome(),
                bairro.getStatus(),
                converterBairroParaBairroCompletoDTO( bairro.getCodigoMunicipio())
        );
    }

    private MunicipioCompletoDTO converterBairroParaBairroCompletoDTO(Municipio municipio) {
        return new MunicipioCompletoDTO(
                municipio.getCodigoMunicipio(),
                municipio.getCodigoUF().getCodigoUF(),
                municipio.getNome(),
                municipio.getStatus(),
                converterUFParaUFCompletoDTO(municipio.getCodigoUF())
        );
    }

    private UFCompletoTDO converterUFParaUFCompletoDTO(UF uf) {
        return new UFCompletoTDO(
                uf.getCodigoUF(),
                uf.getSigla(),
                uf.getNome(),
                uf.getStatus()
        );

    }

    private PessoaCompletaComEnderecosDTO convertePessoaParaPessoaCompletaComEnderecosDTO(Pessoa pessoa) {
        PessoaCompletaComEnderecosDTO pessoaCompletaComEnderecosDTO = new PessoaCompletaComEnderecosDTO();

        pessoaCompletaComEnderecosDTO.setCodigoPessoa(pessoa.getCodigoPessoa());
        pessoaCompletaComEnderecosDTO.setNome(pessoa.getNome());
        pessoaCompletaComEnderecosDTO.setSobrenome(pessoa.getSobrenome());
        pessoaCompletaComEnderecosDTO.setIdade(pessoa.getIdade());
        pessoaCompletaComEnderecosDTO.setLogin(pessoa.getLogin());
        pessoaCompletaComEnderecosDTO.setSenha(pessoa.getSenha());
        pessoaCompletaComEnderecosDTO.setStatus(pessoa.getStatus());

        return pessoaCompletaComEnderecosDTO;
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
