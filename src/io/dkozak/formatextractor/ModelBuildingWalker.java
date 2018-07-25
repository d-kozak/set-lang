package io.dkozak.formatextractor;

import io.dkozak.formatextractor.model.InnerMySet;
import io.dkozak.formatextractor.model.SetElement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class ModelBuildingWalker extends SetBaseListener {

    private final Consumer<String> log;

    private Deque<InnerMySet> setStack = new ArrayDeque<>();

    private InnerMySet currentSet;

    public ModelBuildingWalker(Consumer<String> log) {
        this.log = log;
    }


    @Override
    public void enterEmptySet(SetParser.EmptySetContext ctx) {
        enterSet();
    }

    @Override
    public void enterNonEmptySet(SetParser.NonEmptySetContext ctx) {
        enterSet();
    }

    @Override
    public void exitEmptySet(SetParser.EmptySetContext ctx) {
        exitSet();
    }

    @Override
    public void exitNonEmptySet(SetParser.NonEmptySetContext ctx) {
        exitSet();
    }

    private void enterSet() {
        log.accept("adding set");
        InnerMySet innerSetElement = new InnerMySet();
        if (currentSet != null) {
            currentSet.addElement(innerSetElement);
        }
        currentSet = innerSetElement;
        setStack.addLast(innerSetElement);
    }


    private void exitSet() {
        currentSet = setStack.removeLast();
    }

    @Override
    public void enterSimpleElement(SetParser.SimpleElementContext ctx) {
        String name = ctx.ELEM()
                         .getText();
        log.accept("adding elem " + name);
        currentSet.addElement(new SetElement(name));
    }


    public InnerMySet getSet() {
        return currentSet;
    }
}
