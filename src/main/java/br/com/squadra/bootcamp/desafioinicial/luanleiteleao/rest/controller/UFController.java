package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.controller;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFCustomRepository;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTO;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest.dto.UFDTOComId;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.service.imp.UFServideImp;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/uf")
public class UFController {

    private UFServideImp service;
    private UFCustomRepository ufCustomRepository;

    public UFController(UFServideImp service, br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFCustomRepository ufCustomRepository) {
        this.service = service;
        this.ufCustomRepository = ufCustomRepository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public List<UF> salvar(@RequestBody @Valid UFDTO ufDTO){
        return service.salvar(ufDTO);
    }

    @DeleteMapping
    public List<UF> deletar(Long codigoUF){
        return service.deletar(codigoUF);
    }

    @PutMapping
    public List<UF> atualizar(@RequestBody @Valid UFDTOComId uf){
       return service.atualizar(uf);
    }

//    @GetMapping
//    public Object listarTodasUfs(UF uf){
//
//        if(uf.getCodigoUF()!=null){
//            return service.buscarPorUFcodigoUF(uf.getCodigoUF());
//        } else if (uf.getSigla()!=null) {
//            return service.buscarPorUFSigla(uf.getSigla());
//        }else if(uf.getNome()!=null){
//            return service.buscarPorUFNome(uf.getNome());
//        }else if (uf.getStatus()!=null) {
//            return service.buscarPorUFStatus(uf.getStatus());
//        }
//
//        return service.ListarTodos();
//    }

    @GetMapping
    public Object findUFByCustom(
            @RequestParam(value = "codigoUF", required = false) Long codigoUF,
            @RequestParam(value = "sigla", required = false) String sigla,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) Integer status
    ) {


        return ufCustomRepository.find(codigoUF,sigla,nome,status);

    }


//    @GetMapping(params = "codigoUF")
//    public UF getUFPorCodigoUf(@RequestParam long codigoUF){
//        return service.buscarPorUFcodigoUF(codigoUF);
//    }
//    @GetMapping(params = "sigla")
//    public UF getUFPorSigla(@RequestParam String sigla){
//       return service.buscarPorUFSigla(sigla);
//    }
//
//    @GetMapping(params = "nome")
//    public UF getUFPorNome(@RequestParam String nome){
//        return service.buscarPorUFNome(nome);
//    }

//    @GetMapping(params = "status")
//    public List<UF> getUFPorStatus(@RequestParam Integer status){
//        return service.buscarPorUFStatus(status);
//    }



}


