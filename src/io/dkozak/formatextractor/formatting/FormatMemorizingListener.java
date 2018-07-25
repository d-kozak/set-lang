package io.dkozak.formatextractor.formatting;

import io.dkozak.formatextractor.SetBaseListener;
import io.dkozak.formatextractor.SetParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.*;

public class FormatMemorizingListener extends SetBaseListener {

    private final Map<MapKey, List<FormatInfo>> formatInfoMap = new HashMap<>();

    private final SetParser setParser;

    public FormatMemorizingListener(SetParser setParser) {
        this.setParser = setParser;
    }

    @Override
    public void enterEmptySet(SetParser.EmptySetContext ctx) {
        Token leftBracketNode = ((TerminalNodeImpl) ctx.children.get(0)).symbol;
        Token rightBracketNode = ((TerminalNodeImpl) ctx.children.get(1)).symbol;

        List<String> context = createRuleContext(ctx);

        int appendedNewlines = rightBracketNode.getLine() - leftBracketNode.getLine();

        formatInfoMap.put(new MapKey(context, "emptySet"), Collections.singletonList(new FormatInfo(appendedNewlines, 0)));
    }


    private void saveInfoAboutNextTwoTokens(Token currentToken, Token nextToken, List<FormatInfo> formatInfoList) {
        int appendNewLines = nextToken.getLine() - currentToken.getLine();
        int childrenIndentation = nextToken.getCharPositionInLine() - (currentToken.getCharPositionInLine() + currentToken.getText()
                                                                                                                          .length());
        if (childrenIndentation < 0)
            childrenIndentation = 0;

        if (appendNewLines > 0) {
            childrenIndentation += currentToken.getText()
                                               .length();
        }
        formatInfoList.add(new FormatInfo(appendNewLines, childrenIndentation));
    }

    @Override
    public void enterNonEmptySet(SetParser.NonEmptySetContext ctx) {
        //alternative: '{' elem (',' elem)* '}'

        List<FormatInfo> infos = new ArrayList<>();

        List<String> context = createRuleContext(ctx);

        // first '{' and elem
        Token currentToken = ctx.start;
        Token nextToken = getLeftmostToken(ctx.children.get(1));
        saveInfoAboutNextTwoTokens(currentToken, nextToken, infos);

        // then elem ','
        currentToken = nextToken;
        nextToken = ((TerminalNodeImpl) ctx.children.get(2)).symbol;
        saveInfoAboutNextTwoTokens(currentToken, nextToken, infos);

        // then (',' elem)* , if there are any
        if (ctx.children.size() > 4) {

            currentToken = nextToken;
            nextToken = getLeftmostToken(ctx.children.get(3));
            saveInfoAboutNextTwoTokens(currentToken, nextToken, infos);
        }

        // finally elem '}'
        currentToken = getRightmostToken(ctx.children.get(ctx.children.size() - 2));
        nextToken = ((TerminalNodeImpl) ctx.children.get(ctx.children.size() - 1)).symbol;
        saveInfoAboutNextTwoTokens(currentToken, nextToken, infos);

        formatInfoMap.put(new MapKey(context, "nonEmptySet"), infos);
    }

    private List<String> createRuleContext(ParserRuleContext ctx) {
        String contextAsString = ctx.toString(Arrays.asList(setParser.getRuleNames()));
        contextAsString = contextAsString.substring(1, contextAsString.length() - 1)
                                         .replace("compilationUnit", "")
                                         .trim();
        List<String> context = Arrays.asList(contextAsString.split(" "));
        Collections.reverse(context);
        return context;
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

    public Map<MapKey, List<FormatInfo>> getFormatInfo() {
        return formatInfoMap;
    }
}
