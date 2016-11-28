package com.dereklmclean.styx.schema.atoms;

/**
 * Created by derek on 9/16/16.
 */
public interface Atom {
    String generate();
    double entropy();
}
