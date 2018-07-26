package io.dkozak.setlang;

import io.dkozak.setlang.formatting.FormatInfo;
import io.dkozak.setlang.formatting.FormatMemorizingListener;
import io.dkozak.setlang.formatting.MapKey;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.stream.Collectors.joining;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("One argument needed, the input file");
        }
        String input = Files.lines(Paths.get(args[0]))
                            .collect(joining("\n"));

        String output = process(input, System.out::println);
        System.out.println("Output:\n====\n" + output + "====\n");


    }

    public static String process(String input, Consumer<String> simpleLog) {
        SetLexer lexer = new SetLexer(new ANTLRInputStream(input));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        SetParser parser = new SetParser(commonTokenStream);
        ParseTree ast = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        ModelBuildingWalker listener = new ModelBuildingWalker(simpleLog);
        walker.walk(listener, ast);

        simpleLog.accept("Parsed model is " + listener.getSet());

        FormatMemorizingListener formatMemorizingListener = new FormatMemorizingListener(parser);
        walker.walk(formatMemorizingListener, ast);

        Map<MapKey, List<FormatInfo>> formatInfo = formatMemorizingListener.getFormatInfo();
        simpleLog.accept("ParsedFormatInfo " + formatInfo);


        simpleLog.accept("Input:\n====\n" + input + "====\n");
        return SetToStringConverter.convert(listener.getSet(), formatInfo);
    }
}
