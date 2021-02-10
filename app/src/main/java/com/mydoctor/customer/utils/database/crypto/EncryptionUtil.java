package com.mydoctor.customer.utils.database.crypto;

import android.util.Base64;

import com.mydoctor.customer.utils.Logger;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

    /**
     * Change this KEY for every project.
     * If possible get this KEY using api
     */
    private static final String KEY = "TgSqMVyBt8cXkhX9lblcUXD2HuEn5CSj";

    private EncryptionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generate a random Initialization vector KEY for encryption
     *
     * @return
     */
    public static byte[] generateRandomIV() {
        try {
            SecureRandom randomSecureRandom = new SecureRandom();
            byte[] iv = new byte[16];
            randomSecureRandom.nextBytes(iv);

            IvParameterSpec ivParams = new IvParameterSpec(iv);

            return ivParams.getIV();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return new byte[0];
    }

    private static byte[] encrypt(String ivStr, byte[] bytes)
            throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        MessageDigest md = getMessageDigestInstance("MD5");
        md.update(ivStr.getBytes(StandardCharsets.UTF_8));
        byte[] ivBytes = md.digest();

        MessageDigest sha = getMessageDigestInstance("SHA-256");
        sha.update(EncryptionUtil.KEY.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = sha.digest();

        return encrypt(ivBytes, keyBytes, bytes);
    }

    private static byte[] encrypt(byte[] ivBytes, byte[] keyBytes, byte[] bytes)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        return cipher.doFinal(bytes);
    }

    private static byte[] decrypt(String ivStr, byte[] bytes)
            throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {

        MessageDigest md = getMessageDigestInstance("MD5");
        md.update(ivStr.getBytes(StandardCharsets.UTF_8));
        byte[] ivBytes = md.digest();

        MessageDigest sha = getMessageDigestInstance("SHA-256");
        sha.update(EncryptionUtil.KEY.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = sha.digest();

        return decrypt(ivBytes, keyBytes, bytes);
    }

    private static byte[] decrypt(byte[] ivBytes, byte[] keyBytes, byte[] bytes)
            throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return cipher.doFinal(bytes);
    }

    public static String getEncryptedValueFrom(String enStr)
            throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            InvalidAlgorithmParameterException, BadPaddingException {

        String ivStr = Base64.encodeToString(generateRandomIV(), Base64.DEFAULT);
        byte[] bytes = encrypt(ivStr.trim(), enStr.getBytes(StandardCharsets.UTF_8));
        String encryptedPassword = Base64.encodeToString(bytes, Base64.DEFAULT);
        return (ivStr.trim() + "_" + encryptedPassword);
    }

    public static String getDecryptedValueFrom(String deStr)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException,
            InvalidAlgorithmParameterException, InvalidKeyException {

        String ivStr = deStr.split("_")[0];
        byte[] passByte = Base64.decode(deStr.split("_")[1], Base64.DEFAULT);
        byte[] bytes = decrypt(ivStr, passByte);

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * No sonar added for security hotspot
     *
     * @return
     */
    private static Cipher getCipherInstance() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance("AES/GCM/NoPadding");//NOSONAR
    }

    /**
     * No sonar added for security hotspot
     *
     * @param value
     * @return
     */
    private static MessageDigest getMessageDigestInstance(String value) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(value);//NOSONAR
    }
}
