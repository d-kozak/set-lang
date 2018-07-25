package io.dkozak.formatextractor;

import io.dkozak.formatextractor.model.InnerMySet;
import io.dkozak.formatextractor.model.MySet;
import io.dkozak.formatextractor.model.SetElement;

import static java.util.stream.Collectors.joining;

public class SimpleSetToStringConverter {

    public static String convert(MySet mySet) {
        if (mySet instanceof SetElement)
            return ((SetElement) mySet).getName();
        else if (mySet instanceof InnerMySet) {
            return ((InnerMySet) mySet).getElements()
                                       .stream()
                                       .map(SimpleSetToStringConverter::convert)
                                       .collect(joining(",", "{", "}"));
        } else throw new UnsupportedOperationException("Unkown MySetSubtype: " + mySet.getClass()
                                                                                      .getName());
    }
}
