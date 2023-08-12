package top.yat.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JasyptApplicationTests {

	@Resource
	private StringEncryptor encryptor;

	private static final String PRIVATE_KEY = "jasypt";

	private static BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

	static {
		//basicTextEncryptor.setPassword(PRIVATE_KEY);
	}

	@Test
	void getEncryptString() {

		String url = encryptor.encrypt("jdbc:mysql://101.42.137.243:3306/saToken?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8");

		String username = encryptor.encrypt("root");
		String password = encryptor.encrypt("Xue521110");
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
	}

}
