package io.dkozak.formatextractor.formatting;

import io.dkozak.formatextractor.SetBaseListener;
import io.dkozak.formatextractor.SetParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.*;

public class FormatMemorizingListener extends SetBaseListener {

    private final Map<MapKey, List<FormatInfo>> formatInfoMap = new HashMap<>();

    private final CommonTokenStream commonTokenStream;

    private final SetParser setParser;

    public FormatMemorizingListener(CommonTokenStream commonTokenStream, SetParser setParser) {
        this.commonTokenStream = commonTokenStream;
        this.setParser = setParser;
    }

    @Override
    public void enterEmptySet(SetParser.EmptySetContext ctx) {
        Token leftBracketNode = ((TerminalNodeImpl) ctx.children.get(0)).symbol;
        Token rightBracketNode = ((TerminalNodeImpl) ctx.children.get(1)).symbol;

        String context = ctx.toString(Arrays.asList(setParser.getRuleNames()));
        context = context.substring(1, context.length() - 1)
                         .replace("compilationUnit", "")
                         .trim();

        int appendedNewlines = rightBracketNode.getLine() - leftBracketNode.getLine();

        formatInfoMap.put(new MapKey(context, "emptySet"), Collections.singletonList(new FormatInfo(appendedNewlines, 0)));
    }

    @Override
    public void enterNonEmptySet(SetParser.NonEmptySetContext ctx) {
        //alternative: '{' elem (',' elem)* '}'

        List<FormatInfo> infos = new ArrayList<>();

        String context = ctx.toString(Arrays.asList(setParser.getRuleNames()));
        context = context.substring(1, context.length() - 1)
                         .replace("compilationUnit", "")
                         .trim();

        // first '{' and elem
        Token currentToken = ctx.start;
        Token nextToken = getLeftmostToken(ctx.children.get(1));
        int appendNewLines = nextToken.getLine() - currentToken.getLine();
        int childrenIndentation = nextToken.getCharPositionInLine() - (currentToken.getCharPositionInLine() + currentToken.getText()
                                                                                                                          .length());
        infos.add(new FormatInfo(appendNewLines, childrenIndentation));

        // then elem ','
        currentToken = nextToken;
        nextToken = ((TerminalNodeImpl) ctx.children.get(2)).symbol;
        appendNewLines = nextToken.getLine() - currentToken.getLine();
        childrenIndentation = nextToken.getCharPositionInLine() - (currentToken.getCharPositionInLine() + currentToken.getText()
                                                                                                                      .length());
        infos.add(new FormatInfo(appendNewLines, childrenIndentation));

        if (ctx.children.size() > 4) {
            // then ',' elem
            currentToken = nextToken;
            nextToken = getLeftmostToken(ctx.children.get(3));
            appendNewLines = nextToken.getLine() - currentToken.getLine();
            childrenIndentation = nextToken.getCharPositionInLine() - (currentToken.getCharPositionInLine() + currentToken.getText()
                                                                                                                          .length());
            infos.add(new FormatInfo(appendNewLines, childrenIndentation));
        }

        // finally elem '}'
        currentToken = getRightmostToken(ctx.children.get(ctx.children.size() - 2));
        nextToken = ((TerminalNodeImpl) ctx.children.get(ctx.children.size() - 1)).symbol;
        appendNewLines = nextToken.getLine() - currentToken.getLine();
        childrenIndentation = nextToken.getCharPositionInLine() - (currentToken.getCharPositionInLine() + currentToken.getText()
                                                                                                                      .length());
        infos.add(new FormatInfo(appendNewLines, childrenIndentation));

        formatInfoMap.put(new MapKey(context, "nonEmptySet"), infos);
    }


    public Map<MapKey, List<FormatInfo>> getFormatInfo() {
        return formatInfoMap;
    }


    private Token findNextToken(List<ParseTree> elems) {
        for (ParseTree elem : elems) {
            if (elem instanceof TerminalNodeImpl) {
                return ((TerminalNodeImpl) elem).symbol;
            }
        }
        throw new IllegalArgumentException("No next token found");
    }

    private Token getRightmostToken(ParseTree tree) {
        while (!(tree instanceof TerminalNodeImpl)) {
            if (tree.getChildCount() == 0) {
                throw new IllegalArgumentException("Cannot find rightmost terminal node");
            }
            tree = tree.getChild(tree.getChildCount() - 1);
        }
        return ((TerminalNodeImpl) tree).symbol;
    }

    private Token getLeftmostToken(ParseTree tree) {
        while (!(tree instanceof TerminalNodeImpl)) {
            if (tree.getChildCount() == 0) {
                throw new IllegalArgumentException("Cannot find leftmost terminal node");
            }
            tree = tree.getChild(0);
        }
        return ((TerminalNodeImpl) tree).symbol;
    }

}
