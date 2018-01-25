package org.chen.fish.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedOutputStream;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class AES2Util {
    // 密钥
    public static String key = "AD42F6697B035B75";
    private static String charset = "utf-8";
    // 偏移量
    private static int offset = 16;
    private static String transformation = "AES/CBC/PKCS5Padding";
    private static String algorithm = "AES";

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static byte[] encrypt(String content) {
        return encrypt(content, key);
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(byte[] content) {
        return decrypt(content, key);
    }

    /**
     * 加密
     *
     * @param content
     *            需要加密的内容
     * @param key
     *            加密密码
     * @return
     */
    public static byte[] encrypt(String content, String key) {
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, offset);
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes(charset);
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES（256）解密
     *
     * @param content
     *            待解密内容
     * @param key
     *            解密密钥
     * @return 解密之后
     * @throws Exception
     */
    public static String decrypt(byte[] content, String key) {
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, offset);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, skey, iv);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result); // 解密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
