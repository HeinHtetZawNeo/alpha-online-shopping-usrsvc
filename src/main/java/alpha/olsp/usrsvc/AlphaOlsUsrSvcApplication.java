package alpha.olsp.usrsvc;

import alpha.olsp.usrsvc.model.State;
import alpha.olsp.usrsvc.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlphaOlsUsrSvcApplication {

    @Autowired
    StateRepository stateRepository;

    public static void main(String[] args) {
        SpringApplication.run(AlphaOlsUsrSvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            stateRepository.save(new State("IA", "Iowa"));
        };
    }

}
