package gdc.person.personService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value= {"gdc"})
@EntityScan("gdc.person.datamanager")
@EnableJpaRepositories("gdc.person.datamanager")
public class PersonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServiceApplication.class, args);
	}

}
