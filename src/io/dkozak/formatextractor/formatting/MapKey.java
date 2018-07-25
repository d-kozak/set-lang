package io.dkozak.formatextractor.formatting;

import java.util.Objects;

public final class MapKey {
    public final String context;
    public final String rule;

    public MapKey(String context, String rule) {
        this.context = context;
        this.rule = rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapKey mapKey = (MapKey) o;
        return
                Objects.equals(context, mapKey.context) &&
                        Objects.equals(rule, mapKey.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, rule);
    }

    @Override
    public String toString() {
        return "MapKey{" +
                "context='" + context + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
