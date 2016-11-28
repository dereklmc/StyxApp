package com.dereklmclean.styx.schema;

import com.dereklmclean.styx.RandomNumberGenerator;

/**
 * Created by derek on 9/16/16.
 */
public class CharSet implements Generator {

    private final char[] mCharSet;
    private final RandomNumberGenerator mRng;

    public CharSet(final char[] charSet) {
        mCharSet = charSet;
        mRng = new RandomNumberGenerator();
    }

    @Override
    public String generate() {
        final int index = mRng.getInt(mCharSet.length);
        return Character.toString(mCharSet[index]);
    }

    @Override
    public double entropy() {
        return Math.ceil(Math.log(mCharSet.length) / Math.log(2));
    }
}
