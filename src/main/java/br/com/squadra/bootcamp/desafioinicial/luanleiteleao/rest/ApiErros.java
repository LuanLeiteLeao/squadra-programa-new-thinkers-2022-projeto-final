package br.com.squadra.bootcamp.desafioinicial.luanleiteleao.rest;

import org.springframework.http.HttpStatus;

import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ApiErros {
     private Integer status;
     private  String mensagem;


     public ApiErros(HttpStatus status, String mensagem) {
          this.status =  status.value();
          this.mensagem = mensagem;
     }
     public ApiErros(Integer status, String mensagem) {
          this.status =  status;
          this.mensagem = mensagem;
     }

     public Integer getStatus() {
          return status;
     }

     public void setStatus(Integer status) {
          this.status = status;
     }

     public String getMensagem() {
          return mensagem;
     }

     public void setMensagem(String mensagem) {
          this.mensagem = mensagem;
     }
}
