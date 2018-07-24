package io.dkozak.formatextractor.model;

public final class SetElement implements MySet {
    private final String name;

    public SetElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
