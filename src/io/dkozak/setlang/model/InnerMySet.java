package io.dkozak.setlang.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class InnerMySet implements MySet {

    private final Set<MySet> elements = new LinkedHashSet<>();

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

    public int size() {
        return elements.size();
    }
}
