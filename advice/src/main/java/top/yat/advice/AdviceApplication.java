package top.yat.advice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AdviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdviceApplication.class, args);
    }

}
