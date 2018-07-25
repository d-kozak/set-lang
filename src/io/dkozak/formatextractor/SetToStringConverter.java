package io.dkozak.formatextractor;

import io.dkozak.formatextractor.formatting.FormatInfo;
import io.dkozak.formatextractor.formatting.MapKey;
import io.dkozak.formatextractor.model.InnerMySet;
import io.dkozak.formatextractor.model.MySet;
import io.dkozak.formatextractor.model.SetElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetToStringConverter extends SetBaseListener {

    public static String convert(MySet mySet, Map<MapKey, List<FormatInfo>> formatInfo) {
        return convert(new ArrayList<>(), mySet, formatInfo);
    }

    private static String convert(List<String> context, MySet mySet, Map<MapKey, List<FormatInfo>> formatInfo) {
        if (mySet instanceof SetElement)
            return convertSetElem((SetElement) mySet);
        else if (mySet instanceof InnerMySet) {
            if (!context.isEmpty()) {
                context.add("elem");
            }
            InnerMySet set = (InnerMySet) mySet;
            if (set.size() == 0) {
                return convertEmptySet(context, formatInfo);
            } else {
                return convertNonEmptySet(context, formatInfo, set);
            }
        } else throw new UnsupportedOperationException("Unknown MySetSubtype: " + mySet.getClass()
                                                                                       .getName());
    }

    private static String convertSetElem(SetElement mySet) {
        return mySet.getName();
    }

    private static String convertNonEmptySet(List<String> context, Map<MapKey, List<FormatInfo>> formatInfo, InnerMySet set) {
        context.add("set");
        List<FormatInfo> formatInfoList = formatInfo.get(new MapKey(context, "nonEmptySet"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        appendChar('\n', stringBuilder, formatInfoList.get(0).appendNewLines);
        int childrenIndentation = formatInfoList.get(0).childrenIndentation;

        ArrayList<MySet> setArrayList = new ArrayList<>(set.getElements());
        for (int j = 0; j < setArrayList.size(); j++) {
            MySet element = setArrayList.get(j);
            appendChar(' ', stringBuilder, childrenIndentation);
            stringBuilder.append(convert(new ArrayList<>(context), element, formatInfo));
            appendChar('\n', stringBuilder, formatInfoList.get(1).appendNewLines);
            if (formatInfoList.get(1).appendNewLines > 0) {
                appendChar(' ', stringBuilder, childrenIndentation);
            }
            boolean isNotLast = j < setArrayList.size() - 1;
            boolean moreThanTwoElems = setArrayList.size() > 1;
            if (isNotLast && moreThanTwoElems) {
                stringBuilder.append(',');
                appendChar('\n', stringBuilder, formatInfoList.get(2).appendNewLines);
            }
        }
        appendChar('\n', stringBuilder, formatInfoList.get(formatInfo.size() - 1).appendNewLines);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private static String convertEmptySet(List<String> context, Map<MapKey, List<FormatInfo>> formatInfo) {
        context.add("set");
        FormatInfo firstToSecondBracketRule = formatInfo.get(new MapKey(context, "emptySet"))
                                                        .get(0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        appendChar('\n', stringBuilder, firstToSecondBracketRule.appendNewLines);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private static void appendChar(char c, StringBuilder stringBuilder) {
        appendChar(c, stringBuilder, 1);
    }

    private static void appendChar(char c, StringBuilder stringBuilder, int times) {
        for (int i = 0; i < times; i++) {
            stringBuilder.append(c);
        }
    }
}
