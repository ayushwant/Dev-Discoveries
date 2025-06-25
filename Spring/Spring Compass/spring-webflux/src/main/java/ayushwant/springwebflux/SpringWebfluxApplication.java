package ayushwant.springwebflux;

import ayushwant.springwebflux.keyboard.UncleServiceImpl;
import ayushwant.springwebflux.keyboard.WirelessKeyboard;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);
    }

}
