package com.dereklmclean.styx;

public interface WordList {
    int size();

    int getCount(int length);

    String getWord(int index);

    String getWord(int index, final int length);
}
