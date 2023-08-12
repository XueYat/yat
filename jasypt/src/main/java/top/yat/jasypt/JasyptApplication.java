package top.yat.jasypt;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JasyptApplication {

	/**
	 * 方式一：启动类增加秘钥，程序启动时直接读取
	 * 方式二：命令行启动时增加参数：-Djasypt.encryptor.password=secretkey
	 * @param args
	 */
	public static void main(String[] args) {
		//BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
		//basicTextEncryptor.setPassword("jasypt");
		SpringApplication.run(JasyptApplication.class, args);
	}

}
