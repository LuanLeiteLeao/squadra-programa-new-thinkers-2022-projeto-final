package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.MunicipioCustomRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.MunicipioDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.MunicipioServiceImp;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<MunicipioDTO> salvar(@RequestBody @Valid MunicipioDTO municipioDTO){
        return service.salvar(municipioDTO);
    }

    @PutMapping
    public List<MunicipioDTO> atualizar(@RequestBody @Valid MunicipioDTO municipioDTO){
        return service.atualizar(municipioDTO);
    }

    @DeleteMapping
    public List<MunicipioDTO> deletar(@RequestParam Long codigoMunicipio){
        return service.deletar(codigoMunicipio);
    }

    @GetMapping
    public Object findPersonByCustom(
            @RequestParam(value = "codigoUF", required = false) Long codigoUF,
            @RequestParam(value = "codigoMunicipio", required = false) Long codigoMunicipio,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) Integer status
    ) {
        return service.findPersonByCustom(codigoMunicipio,codigoUF,nome,status);


    }

//    @GetMapping
//    public List<MunicipioDTO> listarTodos(){
//        //LISTANDO SEM PARÂMETROS, TRAGA TODOS OS REGISTROS DO BANCO DE DADOS
//        return service.listarTodos();
//    }
//
//    @GetMapping(params = "codigoMunicipio")
//    public MunicipioDTO consultandoPorcodigoMunicipio(@RequestParam Long codigoMunicipio){
//        //CONSULTANDO POR codigoMunicipio, TRAGA APENAS UM REGISTRO
//        return service.consultandoPorcodigoMunicipio(codigoMunicipio);
//    }
//
//    @GetMapping(params = "codigoUF")
//    public List<MunicipioDTO> listarPorCodigoUF(@RequestParam Long codigoUF ){
//        //LISTANDO POR codigoUF, TRAGA TODOS OS MUNICÍPIOS DA UF RECEBIDA COMO PARÂMETRO.
//        return service.listarPorCodigoUF(codigoUF);
//
//    }


}
