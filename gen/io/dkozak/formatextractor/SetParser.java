// Generated from /home/dkozak/IdeaProjects/FormatExtractor/src/io/dkozak/formatextractor/Set.g4 by ANTLR 4.7
package io.dkozak.formatextractor;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SetParser extends Parser {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, ELEM = 4, WS = 5;
    public static final int
            RULE_compilationUnit = 0, RULE_set = 1, RULE_elem = 2, RULE_simpleElement = 3;
    public static final String[] ruleNames = {
            "compilationUnit", "set", "elem", "simpleElement"
    };
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\7#\4\2\t\2\4\3\t" +
                    "\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\24\n\3\f\3" +
                    "\16\3\27\13\3\3\3\3\3\5\3\33\n\3\3\4\3\4\5\4\37\n\4\3\5\3\5\3\5\2\2\6" +
                    "\2\4\6\b\2\2\2!\2\n\3\2\2\2\4\32\3\2\2\2\6\36\3\2\2\2\b \3\2\2\2\n\13" +
                    "\5\4\3\2\13\f\7\2\2\3\f\3\3\2\2\2\r\16\7\3\2\2\16\33\7\4\2\2\17\20\7\3" +
                    "\2\2\20\25\5\6\4\2\21\22\7\5\2\2\22\24\5\6\4\2\23\21\3\2\2\2\24\27\3\2" +
                    "\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\30\3\2\2\2\27\25\3\2\2\2\30\31\7\4" +
                    "\2\2\31\33\3\2\2\2\32\r\3\2\2\2\32\17\3\2\2\2\33\5\3\2\2\2\34\37\5\b\5" +
                    "\2\35\37\5\4\3\2\36\34\3\2\2\2\36\35\3\2\2\2\37\7\3\2\2\2 !\7\6\2\2!\t" +
                    "\3\2\2\2\5\25\32\36";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = {
            null, "'{'", "'}'", "','"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, "ELEM", "WS"
    };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
    }

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public SetParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "Set.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final CompilationUnitContext compilationUnit() throws RecognitionException {
        CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_compilationUnit);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(8);
                set();
                setState(9);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SetContext set() throws RecognitionException {
        SetContext _localctx = new SetContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_set);
        int _la;
        try {
            setState(24);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
                case 1:
                    _localctx = new EmptySetContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(11);
                    match(T__0);
                    setState(12);
                    match(T__1);
                }
                break;
                case 2:
                    _localctx = new NonEmptySetContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(13);
                    match(T__0);
                    setState(14);
                    elem();
                    setState(19);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__2) {
                        {
                            {
                                setState(15);
                                match(T__2);
                                setState(16);
                                elem();
                            }
                        }
                        setState(21);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(22);
                    match(T__1);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ElemContext elem() throws RecognitionException {
        ElemContext _localctx = new ElemContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_elem);
        try {
            setState(28);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case ELEM:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(26);
                    simpleElement();
                }
                break;
                case T__0:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(27);
                    set();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SimpleElementContext simpleElement() throws RecognitionException {
        SimpleElementContext _localctx = new SimpleElementContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_simpleElement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(30);
                match(ELEM);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CompilationUnitContext extends ParserRuleContext {
        public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SetContext set() {
            return getRuleContext(SetContext.class, 0);
        }

        public TerminalNode EOF() {
            return getToken(SetParser.EOF, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_compilationUnit;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).enterCompilationUnit(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).exitCompilationUnit(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SetVisitor) return ((SetVisitor<? extends T>) visitor).visitCompilationUnit(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class SetContext extends ParserRuleContext {
        public SetContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SetContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_set;
        }

        public void copyFrom(SetContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class EmptySetContext extends SetContext {
        public EmptySetContext(SetContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).enterEmptySet(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).exitEmptySet(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SetVisitor) return ((SetVisitor<? extends T>) visitor).visitEmptySet(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class NonEmptySetContext extends SetContext {
        public NonEmptySetContext(SetContext ctx) {
            copyFrom(ctx);
        }

        public List<ElemContext> elem() {
            return getRuleContexts(ElemContext.class);
        }

        public ElemContext elem(int i) {
            return getRuleContext(ElemContext.class, i);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).enterNonEmptySet(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).exitNonEmptySet(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SetVisitor) return ((SetVisitor<? extends T>) visitor).visitNonEmptySet(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class ElemContext extends ParserRuleContext {
        public ElemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SimpleElementContext simpleElement() {
            return getRuleContext(SimpleElementContext.class, 0);
        }

        public SetContext set() {
            return getRuleContext(SetContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_elem;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).enterElem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).exitElem(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SetVisitor) return ((SetVisitor<? extends T>) visitor).visitElem(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class SimpleElementContext extends ParserRuleContext {
        public SimpleElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ELEM() {
            return getToken(SetParser.ELEM, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_simpleElement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).enterSimpleElement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SetListener) ((SetListener) listener).exitSimpleElement(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SetVisitor) return ((SetVisitor<? extends T>) visitor).visitSimpleElement(this);
            else return visitor.visitChildren(this);
        }
    }
}