package com.dereklmclean.styx;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by derek on 9/16/16.
 */
public class RandomNumberGenerator {

    private final SecureRandom mRng;

    public RandomNumberGenerator() {
        try {
            mRng = SecureRandom.getInstance("SHA1PRNG");
        } catch (final NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int getInt(final int max) {
        return mRng.nextInt(max);
    }
}
