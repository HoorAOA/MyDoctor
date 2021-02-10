package com.mydoctor.customer.utils.database;

import java.security.SecureRandom;

class KeyGenerationUtil {

    private static final String ALLOWED_CHARACTERS =
            "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private static final int SIZE = 24;

    private KeyGenerationUtil() {
        // empty constructor
    }

    /**
     * Get random String value
     *
     * @return
     */
    public static String getRandomKey() {
        return getRandomKey(SIZE);
    }

    /**
     * Get random String value
     *
     * @param sizeOfRandomString
     * @return
     */
    private static String getRandomKey(final int sizeOfRandomString) {
        SecureRandom random = new SecureRandom();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }

}
