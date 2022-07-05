package ar.edu.unq.desaapp.grupo.a.backenddesaappapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendDesaappApiApplication {

	public static Logger logger = LogManager.getLogger(BackendDesaappApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendDesaappApiApplication.class, args);
	}

}
