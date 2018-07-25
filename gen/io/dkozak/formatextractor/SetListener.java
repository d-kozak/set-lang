// Generated from /home/dkozak/IdeaProjects/FormatExtractor/src/io/dkozak/formatextractor/Set.g4 by ANTLR 4.7
package io.dkozak.formatextractor;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SetParser}.
 */
public interface SetListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by the {@code emptySet}
     * labeled alternative in {@link SetParser#set}.
     *
     * @param ctx the parse tree
     */
    void enterEmptySet(SetParser.EmptySetContext ctx);

    /**
     * Exit a parse tree produced by the {@code emptySet}
     * labeled alternative in {@link SetParser#set}.
     *
     * @param ctx the parse tree
     */
    void exitEmptySet(SetParser.EmptySetContext ctx);

    /**
     * Enter a parse tree produced by the {@code nonEmptySet}
     * labeled alternative in {@link SetParser#set}.
     *
     * @param ctx the parse tree
     */
    void enterNonEmptySet(SetParser.NonEmptySetContext ctx);

    /**
     * Exit a parse tree produced by the {@code nonEmptySet}
     * labeled alternative in {@link SetParser#set}.
     *
     * @param ctx the parse tree
     */
    void exitNonEmptySet(SetParser.NonEmptySetContext ctx);

    /**
     * Enter a parse tree produced by {@link SetParser#elem}.
     *
     * @param ctx the parse tree
     */
    void enterElem(SetParser.ElemContext ctx);

    /**
     * Exit a parse tree produced by {@link SetParser#elem}.
     *
     * @param ctx the parse tree
     */
    void exitElem(SetParser.ElemContext ctx);

    /**
     * Enter a parse tree produced by {@link SetParser#simpleElement}.
     *
     * @param ctx the parse tree
     */
    void enterSimpleElement(SetParser.SimpleElementContext ctx);

    /**
     * Exit a parse tree produced by {@link SetParser#simpleElement}.
     *
     * @param ctx the parse tree
     */
    void exitSimpleElement(SetParser.SimpleElementContext ctx);
}