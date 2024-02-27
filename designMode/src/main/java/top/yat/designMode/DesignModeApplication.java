package top.yat.designMode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"top.yat.*"})
public class DesignModeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignModeApplication.class, args);
    }

}
