package com.mydoctor.customer.utils.database.crypto;

public class CryptoConstants {

    /**
     * *
     * File name for Encryption Info storage
     */
    public static final String KEY_FILE_NAME = "Key.txt";
    /**
     * File name for encryption IV Info storage
     */
    public static final String IV_FILE_NAME = "Iv.txt";
    /**
     * TRANSFORMATION Encoding Format
     */
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";
    /**
     * ANDROID_KEY_STORE Name
     */
    public static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    /**
     * Alias
     */
    public static final String ALIAS = "ALIAS_ENC_DEC";

    private CryptoConstants() {
        // empty constructor
    }
}
