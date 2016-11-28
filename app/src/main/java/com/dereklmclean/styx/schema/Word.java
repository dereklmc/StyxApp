package com.dereklmclean.styx.schema;

import com.dereklmclean.styx.WordList;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Word implements Generator {
    private final Random mRng;
    private final int mWordCount;
    private final WordList mWordList;

    public Word(final WordList wordList) {
        mWordList = wordList;
        try {
            mRng = SecureRandom.getInstance("SHA1PRNG");
        } catch (final NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        mWordCount = mWordList.getCount(6);
    }

    @Override
    public String generate() {
        int index = mRng.nextInt(mWordCount);
        return mWordList.getWord(index, 6);
    }

    @Override
    public double entropy() {
        return Math.ceil(Math.log(mWordCount)/Math.log(2));
    }

}
