package com.mydoctor.customer.utils.database.crypto;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.annotation.RequiresApi;

import com.mydoctor.customer.utils.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class ExtendedEncryptionUtil {

    private KeyStore keyStore = null;

    private byte[] iv;

    public ExtendedEncryptionUtil() {
        try {
            keyStore = KeyStore.getInstance(CryptoConstants.ANDROID_KEY_STORE);
            keyStore.load(null);
        } catch (KeyStoreException | CertificateException
                | NoSuchAlgorithmException | IOException e) {
            Logger.error(e.getMessage());
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public byte[] encryptText(final String textToEncrypt)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, BadPaddingException,
            IllegalBlockSizeException {

        final Cipher cipher = Cipher.getInstance(CryptoConstants.TRANSFORMATION);
        SecretKey secretKey = generateSecretKey();
        if (secretKey == null) {
            secretKey = getSecretKey();
        }
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        iv = cipher.getIV();
        return cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8));
    }

    private ArrayList<String> getAllSecretKey() {

        ArrayList<String> keyAliases = new ArrayList<>();

        if (keyStore == null) {
            return keyAliases;
        }

        try {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                keyAliases.add(aliases.nextElement());
            }
        } catch (KeyStoreException e) {
            Logger.error(e.getMessage());
            keyAliases = new ArrayList<>();
            return keyAliases;
        }
        return keyAliases;
    }

    public boolean isSecretKeyAliasPresent() {
        ArrayList<String> secretKeyAliases = getAllSecretKey();
        return (secretKeyAliases.contains(CryptoConstants.ALIAS));
    }

    private SecretKey getSecretKey()
            throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException {
        return ((KeyStore.SecretKeyEntry) keyStore.getEntry(CryptoConstants.ALIAS, null)).getSecretKey();
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private SecretKey generateSecretKey()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidAlgorithmParameterException {

        KeyGenerator keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, CryptoConstants.ANDROID_KEY_STORE);

        keyGenerator.init(new KeyGenParameterSpec.Builder(CryptoConstants.ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setRandomizedEncryptionRequired(false)
                .build());

        return keyGenerator.generateKey();

    }

    public byte[] getIv() {
        return iv;
    }

    public String decryptData(final byte[] encryptedData, final byte[] encryptionIv)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        final Cipher cipher = Cipher.getInstance(CryptoConstants.TRANSFORMATION);
        final GCMParameterSpec spec;
        spec = new GCMParameterSpec(128, encryptionIv);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec);
        return new String(cipher.doFinal(encryptedData), StandardCharsets.UTF_8);
    }

}
