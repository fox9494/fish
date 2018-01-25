package org.chen.fish.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class AESUtil {

    public static final String KEY_AES = "AES";

    public static final IvParameterSpec ivSpec = new IvParameterSpec("abcdefghabcdefgh".getBytes());

//    public static String key_real="jhileiopdkui879i";

//    public static final SecretKeySpec key = new SecretKeySpec(key_real.getBytes(), "AES");

//    public static KeyGenerator kgen = null;

//    static {
//        try {
//            kgen = KeyGenerator.getInstance("AES");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    public static Base64 base64 = new Base64();

    public static String  encrypt(String content,String passwd) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES"); ;// 创建AES的Key生产者
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(passwd.getBytes());
        kgen.init(128, random);// 利用用户密码作为随机数初始化出
        // 128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

        byte[] keyEncoded = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
        SecretKeySpec key = new SecretKeySpec(keyEncoded, "AES");// 转换为AES专用密钥


        Cipher cipher = Cipher.getInstance("AES");// 创建密码器

        byte[] byteContent = content.getBytes("utf-8");

        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

        byte[] result = cipher.doFinal(byteContent);// 加密

        return base64.encodeToString(result);
    }

    public static String decrypt(String content, String passwd) throws Exception {
        byte[] real = base64.decode(content);
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(passwd.getBytes());
        kgen.init(128, random);// 利用用户密码作为随机数初始化出
        // 128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

        byte[] keyEncoded = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
        SecretKeySpec key = new SecretKeySpec(keyEncoded, "AES");// 转换为AES专用密钥
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
        byte[] result = cipher.doFinal(real);
        return new String(result); // 明文
    }


    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] hex2byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String byte2hex(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        AESUtil aesUtil=new AESUtil();
//        String key="abcdkjuiyu789678";
//        String my_content="13540675229";

        List<String> phones = Arrays.asList("1818982229","1181877877","13546768889","18989897676","1818982229","1181877877","13546768889","18989897676");
        String key="YUUAtestYUUAtest09987";
        int i = 0;
        int count=0;
        long start = System.currentTimeMillis();

        String encrypt = AESUtil.encrypt(phones.get(0), key);
        long end = System.currentTimeMillis();
        System.out.println("加密后 = " +encrypt+",cost:"+(end-start));
        encrypt="YTBBMx9NEdtAIMQRay9MXw==";
        String real = AESUtil.decrypt(encrypt, key);
        System.out.println(real);
//        while(true){
////            BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
////            System.out.print("请输入一个参数 待加密内容：");
//            String my_content = phones.get(i);
////            System.out.print("请输入二个参数，加密key：");
////            String key = strin.readLine();
//            String encontent= AESUtil.encrypt(my_content,key);
//            long end = System.currentTimeMillis();
//            System.out.println("加密后 = " +encontent+",cost:"+(end-start));
//            count++;
//            i++;
//            i = i%phones.size();
//            System.out.println("调用总次数： " +count);
//
//            System.out.println("总时间cost:"+(end-start));
////            System.out.println("解密后 =" +new String(AESUtil.decrypt(encrypt_str,key)));
//        }
    }
}
