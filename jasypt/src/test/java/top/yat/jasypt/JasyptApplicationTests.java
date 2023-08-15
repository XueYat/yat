package top.yat.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class JasyptApplicationTests {

	@Resource
	private StringEncryptor encryptor;

	private static final String PRIVATE_KEY = "jasypt";

	@Test
	void getEncryptString() {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		EnvironmentPBEConfig config = new EnvironmentPBEConfig();
		// 加密的算法
		config.setAlgorithm("PBEWithMD5AndDES");
		// 加密的秘钥
		config.setPassword(PRIVATE_KEY);
		standardPBEStringEncryptor.setConfig(config);

		String url = "jdbc:mysql://101.42.137.243:3306/saToken?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8";
		String username = "root";
		String password = "root";

		System.out.println("============加密==========");

		String enUrl = encryptor.encrypt(url);
		String enUsername = encryptor.encrypt(username);
		String enPassword = encryptor.encrypt(password);
		System.out.println(enUrl);
		System.out.println(enUsername);
		System.out.println(enPassword);

		String stUrl = standardPBEStringEncryptor.encrypt(url);
		String stUsername = standardPBEStringEncryptor.encrypt(username);
		String stPassword = standardPBEStringEncryptor.encrypt(password);
		System.out.println(stUrl);
		System.out.println(stUsername);
		System.out.println(stPassword);

		System.out.println("============解密==========");

		System.out.println(encryptor.decrypt(enUrl));
		System.out.println(encryptor.decrypt(enUsername));
		System.out.println(encryptor.decrypt(enPassword));

		System.out.println(standardPBEStringEncryptor.decrypt(stUrl));
		System.out.println(standardPBEStringEncryptor.decrypt(stUsername));
		System.out.println(standardPBEStringEncryptor.decrypt(stPassword));
		log.error(stUrl);
		log.info(stUrl);
	}

}
