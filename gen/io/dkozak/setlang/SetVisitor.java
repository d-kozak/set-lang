// Generated from /home/dkozak/IdeaProjects/FormatExtractor/src/io/dkozak/setlang/Set.g4 by ANTLR 4.7
package io.dkozak.setlang;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SetParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SetVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link SetParser#compilationUnit}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCompilationUnit(SetParser.CompilationUnitContext ctx);

    /**
     * Visit a parse tree produced by the {@code emptySet}
     * labeled alternative in {@link SetParser#set}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitEmptySet(SetParser.EmptySetContext ctx);

    /**
     * Visit a parse tree produced by the {@code nonEmptySet}
     * labeled alternative in {@link SetParser#set}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNonEmptySet(SetParser.NonEmptySetContext ctx);

    /**
     * Visit a parse tree produced by {@link SetParser#elem}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitElem(SetParser.ElemContext ctx);

    /**
     * Visit a parse tree produced by {@link SetParser#simpleElement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSimpleElement(SetParser.SimpleElementContext ctx);
}