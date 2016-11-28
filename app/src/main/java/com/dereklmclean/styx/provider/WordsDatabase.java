package com.dereklmclean.styx.provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * TODO
 */
public class WordsDatabase extends SQLiteAssetHelper {
    public static final String WORDS_TABLE = "words";
    public static final String ID_COLUMN = "id";
    public static final String WORD_COLUMN = "word";

    private static final String DATABASE_NAME = "dictionary.db";
    private static final int VERSION = 1; // TODO: Read from values

    public WordsDatabase(final Context context) {
        super(context,DATABASE_NAME,null,VERSION);
        setForcedUpgrade();
    }

    public Cursor getWords() {
        final SQLiteDatabase db = getReadableDatabase();
        final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(WORDS_TABLE);
        final Cursor c = qb.query(db, // Database
                new String[] { ID_COLUMN, WORD_COLUMN }, // Projection
                null, // Where
                null, // Arguments
                null, // GroupBy
                null,
                null // OrderBy
        );
        c.moveToFirst();
        return c;
    }

    public Cursor getSize() {
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor c = db.rawQuery("select count(*) from words", null);
        c.moveToFirst();
        return c;
    }

    public Cursor getCount(final int length) {
        final SQLiteDatabase db = getReadableDatabase();
        final Cursor c = db.rawQuery("select count(*) from words where length(word) <= " + length,
                null);
        c.moveToFirst();
        return c;
    }

    public Cursor getWord(final int index, final int length) {
        final SQLiteDatabase db = getReadableDatabase();
        final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(WORDS_TABLE);
        final Cursor c = qb.query(db, // Database
                new String[] { WORD_COLUMN }, // Projection
                "LENGTH(word) <= " + Integer.toString(length), // Where
                null, // Arguments
                null, // GroupBy
                null,
                null, // OrderBy
                Integer.toString(index) + ",1"
        );
        c.moveToFirst();
        return c;
    }
}
