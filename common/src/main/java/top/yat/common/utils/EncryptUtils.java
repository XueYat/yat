package top.yat.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * @author XueYat
 * @date 2023/07/07
 */
public class EncryptUtils {
    /**
     * DES加密算法
     */
    public final static String ALGORITHM_DES = "DES";

    /**
     * 加密字符串（Java 8提供的Base64，要比sun.misc套件提供的还要快至少11倍，比Apache Commons Codec提供的还要快至少3倍）
     *
     * @param code       需要加密的字符串
     * @param encryptKey 对称密钥串
     * @throws Exception
     */
    public static String encryptDES(String code, String encryptKey) throws Exception {
        //java8提供的类（Base64）
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytesMing = encryptDES(code.getBytes("UTF8"), encryptKey);
        return encoder.encodeToString(bytesMing);
    }

    /**
     * 解密字符串（Java 8提供的Base64，要比sun.misc套件提供的还要快至少11倍，比Apache Commons Codec提供的还要快至少3倍）
     *
     * @param code       需要解密的字符串1
     * @param encryptKey 对称密钥串
     * @throws Exception
     */
    public static String decryptDES(String code, String encryptKey) throws Exception {
        //java8提供的类（Base64）
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(code);
        byte[] bytesMing = decryptDES(decode, encryptKey);
        return new String(bytesMing, "UTF8");
    }


    /**
     * 加密
     *
     * @param bytes      明文字节
     * @param encryptKey 对称密钥串
     * @throws Exception
     */
    public static byte[] encryptDES(byte[] bytes, String encryptKey) throws Exception {
        Key key = generateKey(encryptKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }


    /**
     * 生成密钥
     *
     * @param encryptKey
     * @throws Exception
     */
    public static Key generateKey(String encryptKey) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
        DESKeySpec keySpec = new DESKeySpec(encryptKey.getBytes());
        return keyFactory.generateSecret(keySpec);
    }

    /**
     * 解密
     *
     * @param bytes      密文字节
     * @param encryptKey 对称密钥串
     * @throws Exception
     */
    public static byte[] decryptDES(byte[] bytes, String encryptKey) throws Exception {
        Key key = generateKey(encryptKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }


    /**
     * 方便测试(正常情况下是前端加密请求参数，后端接收到请求参数进行解密操作，执行完逻辑之后，对返回的数据进行加密返回给前端，前端解密获取数据)
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String test = "{\"userId\": 1, \"userName\": \"xueyt\", \"password\": \"xueyt\"}";
        String s = encryptDES(test, "abdel99999999");
        System.out.println("【加密之后数据:】" + s.replaceAll("\\r\\n", ""));

        String a = "37vWxYjDfONQkasC0YVlcM83/qiyKze+2UEr0CB5fVPdcsNVINvDQoP9j9+QRj45r9ECj1Qo4xg=";
        String aa = decryptDES(a, "abdel99999999");
        System.out.println("【解密之后数据:】" + aa);
    }
}
