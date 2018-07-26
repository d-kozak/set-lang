package io.dkozak.setlang;

import io.dkozak.setlang.model.InnerMySet;
import io.dkozak.setlang.model.MySet;
import io.dkozak.setlang.model.SetElement;

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
