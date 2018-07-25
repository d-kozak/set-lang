package io.dkozak.formatextractor;

import io.dkozak.formatextractor.formatting.FormatInfo;
import io.dkozak.formatextractor.formatting.FormatMemorizingListener;
import io.dkozak.formatextractor.formatting.MapKey;
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

        SetLexer lexer = new SetLexer(new ANTLRInputStream(input));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        SetParser parser = new SetParser(commonTokenStream);
        ParseTree ast = parser.set();

        Consumer<String> simpleLog = System.out::println;

        ParseTreeWalker walker = new ParseTreeWalker();
        ModelBuildingWalker listener = new ModelBuildingWalker(simpleLog);
        walker.walk(listener, ast);

        simpleLog.accept("Parsed model is " + listener.getSet());

        FormatMemorizingListener formatMemorizingListener = new FormatMemorizingListener(commonTokenStream, parser);
        walker.walk(formatMemorizingListener, ast);

        System.out.println("Output:\n" + SetToStringConvertor.convert(listener.getSet()));


        Map<MapKey, List<FormatInfo>> formatInfo = formatMemorizingListener.getFormatInfo();

        System.out.println("ParsedFormatInfo " + formatInfo);
    }
}
