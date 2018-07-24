package io.dkozak.formatextractor;

import io.dkozak.formatextractor.model.InnerMySet;
import io.dkozak.formatextractor.model.SetElement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class SetWalker extends SetBaseListener {

    private final Consumer<String> log;

    private Deque<InnerMySet> setStack = new ArrayDeque<>();

    private InnerMySet currentSet;

    public SetWalker(Consumer<String> log) {
        this.log = log;
    }

    @Override
    public void enterSet(SetParser.SetContext ctx) {
        log.accept("adding set");
        InnerMySet innerSetElement = new InnerMySet();
        if (currentSet != null) {
            currentSet.addElement(innerSetElement);
        }
        currentSet = innerSetElement;
        setStack.addLast(innerSetElement);
    }

    @Override
    public void exitSet(SetParser.SetContext ctx) {
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
