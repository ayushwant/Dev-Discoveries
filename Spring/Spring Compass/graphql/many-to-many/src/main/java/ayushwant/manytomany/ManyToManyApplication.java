package ayushwant.manytomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyApplication.class, args);
    }

}
