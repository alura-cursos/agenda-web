package br.com.caelum.alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration	
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}	
}

//dar uns infos das cpoisas acontecendo... log....
//	- requisicao web recebida
//	- requisicao pŕo firebase... cuspir que esta acontecendo e cuspir a msg de resṕosta, cuidado com catch
//	- requisicao vinda do firebase
//	- org.apache, hibernate, spring -> warn. log4j.xml
	