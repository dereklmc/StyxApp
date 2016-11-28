package com.dereklmclean.styx;

import android.database.Cursor;

import com.dereklmclean.styx.provider.WordsDatabase;

/**
 * Created by derek on 9/16/16.
 */
public class WordDbList implements WordList {

    public static final int LONGEST_WORD = 25;
    private final WordsDatabase mWordsDb;

    public WordDbList(WordsDatabase wordsDb) {
        mWordsDb = wordsDb;
    }

    @Override
    public int size() {
        final Cursor c = mWordsDb.getSize();
        return c.getInt(0);
    }
    @Override
    public int getCount(final int length) {
        final Cursor c = mWordsDb.getCount(length);
        return c.getInt(0);
    }

    @Override
    public String getWord(int index) {
        return getWord(index, LONGEST_WORD);
    }

    @Override
    public String getWord(int index, int length) {
        final Cursor c = mWordsDb.getWord(index, length);
        final int wordColumnIndex = c.getColumnIndex(WordsDatabase.WORD_COLUMN);
        return c.getString(wordColumnIndex);
    }
}
