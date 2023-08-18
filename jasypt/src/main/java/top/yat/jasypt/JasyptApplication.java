package top.yat.jasypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEncryptableProperties
public class JasyptApplication {

	/**
	 * 方式一：开启加密注解，启动类增加秘钥，程序启动时直接读取
	 * 方式二：命令行启动时增加参数：-Djasypt.encryptor.password=secretkey
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("jasypt.encryptor.password", "jasypt");
		SpringApplication.run(JasyptApplication.class, args);
	}

}
