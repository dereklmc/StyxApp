package com.dereklmclean.styx.schema.atoms;

import com.dereklmclean.styx.RandomNumberGenerator;

/**
 * Created by derek on 9/16/16.
 */
public class RandomNumber implements Atom {

    private final RandomNumberGenerator mRng;
    private final int mMax;
    private final String mFormat;

    public RandomNumber(final int length) {
        mMax = (int) Math.pow(10, length);
        mFormat = "%0" + Integer.toString(length) + "d";
        mRng = new RandomNumberGenerator();
    }

    @Override
    public String generate() {
        final int random = mRng.getInt(mMax);
        final String number = String.format(mFormat, random);
        return number;
    }

    @Override
    public double entropy() {
        return Math.ceil(Math.log(mMax) / Math.log(2));
    }
}
