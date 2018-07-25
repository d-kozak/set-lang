package io.dkozak.formatextractor.formatting;

public final class FormatInfo {
    public final int appendNewLines;
    public final int childrenIndentation;

    public FormatInfo(int appendNewLines, int childrenIndentation) {
        this.appendNewLines = appendNewLines;
        this.childrenIndentation = childrenIndentation;
    }

    @Override
    public String toString() {
        return "FormatInfo{" +
                "appendNewLines=" + appendNewLines +
                ", childrenIndentation=" + childrenIndentation +
                '}';
    }
}
