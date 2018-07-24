package io.dkozak.formatextractor.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class InnerMySet implements MySet {

    private final Set<MySet> elements = new HashSet<>();

    public void addElement(MySet element) {
        elements.add(element);
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public Set<MySet> getElements() {
        return Collections.unmodifiableSet(elements);
    }
}
