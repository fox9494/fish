package org.chen.fish.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AESUtil {

    public static final String KEY_AES = "AES";

//    public static KeyGenerator kgen = null;

//    static {
//        try {
//            kgen = KeyGenerator.getInstance("AES");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    public static byte[]  encrypt(String content,String passwd) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES"); ;// 创建AES的Key生产者

        kgen.init(128, new SecureRandom(passwd.getBytes()));// 利用用户密码作为随机数初始化出
        // 128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

        byte[] keyEncoded = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
        SecretKeySpec key = new SecretKeySpec(keyEncoded, "AES");// 转换为AES专用密钥


        Cipher cipher = Cipher.getInstance("AES");// 创建密码器

        byte[] byteContent = content.getBytes("utf-8");

        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

        byte[] result = cipher.doFinal(byteContent);// 加密

        return result;
    }

    public static byte[] decrypt(byte[] content,String passwd) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(passwd.getBytes()));// 利用用户密码作为随机数初始化出
        // 128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

        byte[] keyEncoded = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
        SecretKeySpec key = new SecretKeySpec(keyEncoded, "AES");// 转换为AES专用密钥
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
        byte[] result = cipher.doFinal(content);
        return result; // 明文
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
        String key="jkjksjskldfsklfjsdf9ds90f8sdf8s0df8sd0f8s";
        int i = 0;
        int count=0;
        long start = System.currentTimeMillis();
        while(true){
//            BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("请输入一个参数 待加密内容：");
            String my_content = phones.get(i);
//            System.out.print("请输入二个参数，加密key：");
//            String key = strin.readLine();
            byte[] encrypt_str = AESUtil.encrypt(my_content,key);
            long end = System.currentTimeMillis();
            System.out.println("加密后 = " +new String(encrypt_str)+",cost:"+(end-start));
            System.out.println("加密后 hex= " +AESUtil.byte2hex(encrypt_str));
            count++;
            i++;
            i = i%phones.size();
            System.out.println("调用总次数： " +count);

            System.out.println("总时间cost:"+(end-start));
//            System.out.println("解密后 =" +new String(AESUtil.decrypt(encrypt_str,key)));
        }
    }
}
