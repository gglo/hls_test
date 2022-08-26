package com.jiankangyouyi.cloud.course.utils;

import lombok.NonNull;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 *
 * @author : DL
 * @date Date : 2019年07月29日
 */
public class FileEncryptUtils {

    /**
     * 文件加密偏移数
     */
    private static char[] ENCRYPT_OFFSET;

    private static int ENCRYPT_OFFSET_LENGTH;


    static {
        ENCRYPT_OFFSET = BigDecimal.valueOf(853).divide(BigDecimal.valueOf(134), 1024, BigDecimal.ROUND_UP).toString().toCharArray();
        ENCRYPT_OFFSET_LENGTH = ENCRYPT_OFFSET.length;
    }

    /**
     * 文件加密
     *
     * @param file 待加密文件，不能为空
     * @param key  密码，不能为空
     * @return 加密后的byte[]
     */
    public static byte[] encrypt(@NonNull File file, @NonNull String key) throws IOException {

        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException(
                    "this key must have text; it must not be null or empty");
        }

        byte[] fileBytes = FileUtils.readFileToByteArray(file);

        for (int i = 0; i < fileBytes.length; i++) {
            //字节偏移
            fileBytes[i] += ENCRYPT_OFFSET[i % ENCRYPT_OFFSET_LENGTH];

        }

        //在末尾拼接上密码
        return ArrayUtils.addAll(fileBytes, key.getBytes());
    }

    /**
     * 字节加密
     *
     * @param fileBytes 待加密字节，不能为空
     * @param key  密码，不能为空
     * @return 加密后的byte[]
     */
    public static byte[] encrypt(@NonNull byte[] fileBytes, @NonNull String key) throws IOException {

        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException(
                    "this key must have text; it must not be null or empty");
        }
        for (int i = 0; i < fileBytes.length; i++) {
            //字节偏移
            fileBytes[i] += ENCRYPT_OFFSET[i % ENCRYPT_OFFSET_LENGTH];

        }

        //在末尾拼接上密码
        return ArrayUtils.addAll(fileBytes, key.getBytes());
    }


    /**
     * 文件解密
     *
     * @param file 输入的文件
     * @param key  密码
     * @return 解密后的byte[]
     */
    public static byte[] decrypt(@NonNull File file, @NonNull String key) throws IOException {

        if (!isEncrypt(file, key)) {
            return FileUtils.readFileToByteArray(file);
        }

        byte[] fileBytes = FileUtils.readFileToByteArray(file);

        byte[] bytes = key.getBytes();

        int length = fileBytes.length - key.getBytes().length;

        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = fileBytes[i];
            //字节偏移
            result[i] -= ENCRYPT_OFFSET[i % ENCRYPT_OFFSET_LENGTH];

        }

        return result;
    }


    /**
     * 是否对文件进行加密
     *
     * @param file 输入的文件
     * @param key  密码
     * @return true 文件被加密 false 文件未加密
     */
    public static boolean isEncrypt(@NonNull File file, @NonNull String key) throws IOException {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException(
                    "this key must have text; it must not be null or empty");
        }

        int keyLength = key.length();

        byte[] fileBytes = FileUtils.readFileToByteArray(file);

        byte[] parseKeyBytes;
        try {
            parseKeyBytes = ArrayUtils.subarray(
                    fileBytes, fileBytes.length - keyLength, fileBytes.length);
        } catch (Exception e) {
            return false;
        }

        if (parseKeyBytes == null) {
            return false;
        }

        return key.equals(new String(parseKeyBytes));
    }

}
