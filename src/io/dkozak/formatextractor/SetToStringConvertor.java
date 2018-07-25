package io.dkozak.formatextractor;

import io.dkozak.formatextractor.formatting.FormatInfo;
import io.dkozak.formatextractor.formatting.MapKey;
import io.dkozak.formatextractor.model.InnerMySet;
import io.dkozak.formatextractor.model.MySet;
import io.dkozak.formatextractor.model.SetElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class SetToStringConvertor extends SetBaseListener {

    public static String convert(MySet mySet) {
        if (mySet instanceof SetElement)
            return ((SetElement) mySet).getName();
        else if (mySet instanceof InnerMySet) {
            return ((InnerMySet) mySet).getElements()
                                       .stream()
                                       .map(SetToStringConvertor::convert)
                                       .collect(joining(",", "{", "}"));
        } else throw new UnsupportedOperationException("Unkown MySetSubtype: " + mySet.getClass()
                                                                                      .getName());
    }

    public static String convertToFormatted(MySet mySet, Map<MapKey, List<FormatInfo>> formatInfo) {
        return convertToFormatted(new ArrayList<>(), mySet, formatInfo);
    }

    private static String convertToFormatted(List<String> context, MySet mySet, Map<MapKey, List<FormatInfo>> formatInfo) {
        if (mySet instanceof SetElement)
            return ((SetElement) mySet).getName();
        else if (mySet instanceof InnerMySet) {
            if (!context.isEmpty()) {
                context.add("elem");
            }
            InnerMySet set = (InnerMySet) mySet;
            if (set.size() == 0) {
                context.add("set");
                FormatInfo firstToSecondBracketRule = formatInfo.get(new MapKey(context, "emptySet"))
                                                                .get(0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{");
                for (int i = 0; i < firstToSecondBracketRule.appendNewLines; i++) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append("}");
                return stringBuilder.toString();
            } else {
                context.add("set");
                List<FormatInfo> formatInfoList = formatInfo.get(new MapKey(context, "nonEmptySet"));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{");
                for (int i = 0; i < formatInfoList.get(0).appendNewLines; i++) {
                    stringBuilder.append("\n");
                }
                int childrenIndentation = formatInfoList.get(0).childrenIndentation;
                if (childrenIndentation < 0)
                    childrenIndentation = 0;

                ArrayList<MySet> setArrayList = new ArrayList<>(set.getElements());
                for (int j = 0; j < setArrayList.size(); j++) {
                    MySet element = setArrayList.get(j);
                    for (int i = 0; i < childrenIndentation; i++) {
                        stringBuilder.append(' ');
                    }
                    stringBuilder.append(convertToFormatted(new ArrayList<>(context), element, formatInfo));
                    for (int i = 0; i < formatInfoList.get(1).appendNewLines; i++) {
                        stringBuilder.append("\n");
                    }
                    if (formatInfoList.get(1).appendNewLines > 0) {
                        for (int i = 0; i < childrenIndentation; i++) {
                            stringBuilder.append(' ');
                        }
                    }
                    if (j < setArrayList.size() - 1 && setArrayList.size() > 1) {
                        stringBuilder.append(',');
                        for (int i = 0; i < formatInfoList.get(2).appendNewLines; i++) {
                            stringBuilder.append("\n");
                        }
                    }
                }

                int lastIndex;
                if (set.size() == 1) {
                    lastIndex = 2;
                } else lastIndex = 3;
                for (int i = 0; i < formatInfoList.get(lastIndex).appendNewLines; i++) {
                    stringBuilder.append("\n");
                }

                stringBuilder.append("}");
                return stringBuilder.toString();
            }
        } else throw new UnsupportedOperationException("Unknown MySetSubtype: " + mySet.getClass()
                                                                                       .getName());
    }
}
