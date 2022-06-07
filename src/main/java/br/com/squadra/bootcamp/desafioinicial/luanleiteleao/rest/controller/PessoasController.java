package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.*;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.PessoaServiceImp;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/pessoa")
public class PessoasController {

    private PessoaServiceImp service;

    public PessoasController(PessoaServiceImp service) {
        this.service = service;
    }

    @GetMapping
    public List<PessoaRessumidaSemEnderecosDTO> listarTodos(){
        return service.listarTodos();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PessoaDTO salvar(@RequestBody @Valid PessoaDTO pessoa){
        return service.salvar(pessoa);
    }

    @PutMapping
    public PessoaDTO atualizar(@RequestBody @Valid PessoaDTO pessoa){
        return service.atualizar(pessoa);
    }

    @DeleteMapping
    public String delete(Long codigoPessoa){
        service.delete(codigoPessoa);
        return "Deletado";
    }

//    PESQUISAR PESSOA POR codigoPessoa
    @GetMapping(params = "codigoPessoa")
    public PessoaCompletaComEnderecosDTO pesquisarPessoaPorCodigoPessoa( @RequestParam Long codigoPessoa){
       return service.pesquisarPessoaPorCodigoPessoa(codigoPessoa);
    }

}
