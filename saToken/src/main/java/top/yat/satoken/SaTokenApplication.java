package top.yat.satoken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"top.yat.satoken.mapper"})
public class SaTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaTokenApplication.class, args);
	}

}
