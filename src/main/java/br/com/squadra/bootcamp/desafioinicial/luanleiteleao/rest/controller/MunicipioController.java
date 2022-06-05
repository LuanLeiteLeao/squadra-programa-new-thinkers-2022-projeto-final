package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.Municipio;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.MunicipioServiceImp;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {
    private MunicipioServiceImp service;

    public MunicipioController(MunicipioServiceImp service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public MunicipioDTO salvar(@RequestBody @Valid MunicipioDTO municipioDTO){
        return service.salvar(municipioDTO);
    }

    @PutMapping
    public MunicipioDTO atualizar(@RequestBody @Valid MunicipioDTO municipioDTO){
        return service.atualizar(municipioDTO);
    }

    @DeleteMapping
    public List<MunicipioDTO> deletar(@RequestParam Long codigoMunicipio){
        return service.deletar(codigoMunicipio);
    }

//    LISTANDO POR codigoUF, TRAGA TODOS OS MUNICÍPIOS DA UF RECEBIDA COMO PARÂMETRO.
    @GetMapping
    public List<MunicipioDTO> listarTodos(){
        //LISTANDO SEM PARÂMETROS, TRAGA TODOS OS REGISTROS DO BANCO DE DADOS
        return service.listarTodos();
    }

    @GetMapping(params = "codigoMunicipio")
    public MunicipioDTO consultandoPorcodigoMunicipio(@RequestParam Long codigoMunicipio){
        //CONSULTANDO POR codigoMunicipio, TRAGA APENAS UM REGISTRO
        return service.consultandoPorcodigoMunicipio(codigoMunicipio);
    }

}
