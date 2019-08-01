package desafio;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import desafio.repository.*;
import desafio.model.*;
import desafio.service.*;


@ComponentScan
@SpringBootApplication
public class Application {
	
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

	}
	
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, PessoaRepository repository, PessoaService pService){
        return args -> {
	
		/*
		Salvar algumas Pessoas
		TODO: JPA repository populators
		*/

		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		repository.save(new Pessoa("Jack", "jack@desafio.com","10283073900", format.parse("2009-12-31")));
		repository.save(new Pessoa("Chloe", "chloe@desafio.com","10283073900", format.parse("2009-12-31")));
		repository.save(new Pessoa("Chloe2", "chloe@desafio.com","10283073900", format.parse("2009-12-31")));
		repository.save(new Pessoa("Kim", "kim@desafio.com","10283073900", format.parse("2009-12-31")));
		repository.save(new Pessoa("David", "david@desafio.com","10283073900", format.parse("2009-12-31")));
		repository.save(new Pessoa("Michelle", "michelle@desafio.com","10283073900", format.parse("2009-12-31")));
*/
		log.info("Aplicacao Rodando");
		};
		
		
	}
	
	

}
