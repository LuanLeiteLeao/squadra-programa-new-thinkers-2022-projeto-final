package br.com.squadra.bootcamp.desafioinicial.luanleiteleao;

import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.entity.UF;
import br.com.squadra.bootcamp.desafioinicial.luanleiteleao.domain.repository.UFSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired UFSRepository UFSRepository){
        return args -> {
            UFSRepository.save(new UF("SP","sao paulo",1));


//            pessoas.save(new Pessoa("fulano1","dasilva1",12,"login1","senha1",2));
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);
    }
}