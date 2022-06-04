package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.MunicipioServiceImp;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}