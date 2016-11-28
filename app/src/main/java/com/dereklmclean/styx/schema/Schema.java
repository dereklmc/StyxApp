package com.dereklmclean.styx.schema;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Comment
 */
public class Schema implements Generator {

    private final List<Generator> generators = new ArrayList<>();

    public void add(final Generator generator) {
        generators.add(generator);
    }

    @Override
    public String generate() {
        final StringBuilder builder = new StringBuilder();
        for (final Generator gen : generators) {
            builder.append(gen.generate());
        }
        return builder.toString();
    }

    @Override
    public double entropy() {
        double entropy = 0.0;
        for (final Generator gen : generators) {
            entropy += gen.entropy();
        }
        return entropy;
    }
}
