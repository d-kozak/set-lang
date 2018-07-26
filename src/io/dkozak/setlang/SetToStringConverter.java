package io.dkozak.setlang;

import io.dkozak.setlang.formatting.FormatInfo;
import io.dkozak.setlang.formatting.MapKey;
import io.dkozak.setlang.model.InnerMySet;
import io.dkozak.setlang.model.MySet;
import io.dkozak.setlang.model.SetElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetToStringConverter extends SetBaseListener {

    public static String convert(MySet mySet, Map<MapKey, List<FormatInfo>> formatInfo) {
        return convert(new ArrayList<>(), 0, mySet, formatInfo);
    }

    private static String convert(List<String> context, int indentation, MySet mySet, Map<MapKey, List<FormatInfo>> formatInfo) {
        if (mySet instanceof SetElement)
            return convertSetElem(indentation, (SetElement) mySet);
        else if (mySet instanceof InnerMySet) {
            if (!context.isEmpty()) {
                context.add("elem");
            }
            InnerMySet set = (InnerMySet) mySet;
            if (set.size() == 0) {
                return convertEmptySet(context, indentation, formatInfo);
            } else {
                return convertNonEmptySet(context, indentation, formatInfo, set);
            }
        } else throw new UnsupportedOperationException("Unknown MySetSubtype: " + mySet.getClass()
                                                                                       .getName());
    }

    private static String convertSetElem(int indentation, SetElement mySet) {
        StringBuilder stringBuilder = new StringBuilder();
        appendChar(' ', stringBuilder, indentation);
        stringBuilder.append(mySet.getName());
        return stringBuilder.toString();
    }

    private static String convertNonEmptySet(List<String> context, int indentation, Map<MapKey, List<FormatInfo>> formatInfo, InnerMySet set) {
        context.add("set");
        List<FormatInfo> formatInfos = formatInfo.get(new MapKey(context, "nonEmptySet"));
        StringBuilder builder = new StringBuilder();
        appendChar(' ', builder, indentation);
        builder.append('{');
        appendChar('\n', builder, formatInfos.get(0).appendNewLines);
        int childrenIndentation = indentation + formatInfos.get(0).childrenIndentation;

        ArrayList<MySet> setArrayList = new ArrayList<>(set.getElements());
        for (int j = 0; j < setArrayList.size(); j++) {
            MySet element = setArrayList.get(j);
            int innerElementIndentation = formatInfos.get(2).appendNewLines > 0 ? childrenIndentation : formatInfos.get(0).childrenIndentation;
            builder.append(convert(new ArrayList<>(context), innerElementIndentation, element, formatInfo));
            boolean isNotLast = j < setArrayList.size() - 1;
            boolean setHasMoreThanTwoElems = setArrayList.size() > 1;
            if (isNotLast && setHasMoreThanTwoElems) {
                builder.append(',');
                appendChar('\n', builder, formatInfos.get(2).appendNewLines);
            }
        }
        appendChar('\n', builder, formatInfos.get(formatInfos.size() - 1).appendNewLines);
        if (formatInfos.get(formatInfos.size() - 1).appendNewLines > 0) {
            appendChar(' ', builder, indentation);
        }
        builder.append('}');
        return builder.toString();
    }

    private static String convertEmptySet(List<String> context, int indentation, Map<MapKey, List<FormatInfo>> formatInfo) {
        context.add("set");
        FormatInfo firstToSecondBracketRule = formatInfo.get(new MapKey(context, "emptySet"))
                                                        .get(0);
        StringBuilder stringBuilder = new StringBuilder();
        appendChar(' ', stringBuilder, indentation);
        stringBuilder.append('{');
        appendChar('\n', stringBuilder, firstToSecondBracketRule.appendNewLines);
        if (firstToSecondBracketRule.appendNewLines > 0) {
            appendChar(' ', stringBuilder, indentation);
        }
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
