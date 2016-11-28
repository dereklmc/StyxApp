package com.dereklmclean.styx.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * TODO
 */
public class WordProvider extends ContentProvider {

    public static final String AUTHORITY = "com.dereklmclean.styx.wordprovider";
    public static final Uri CONTENT_URI = Uri.fromParts("content", AUTHORITY, null);

//    private static final UriMatcher URI_MATCHER = new UriMatcher();

    private WordsDatabase database;

    @Override
    public boolean onCreate() {
        database = new WordsDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
