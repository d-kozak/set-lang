package io.dkozak.setlang;

import org.junit.Test;

import java.util.function.Consumer;

import static io.dkozak.setlang.Main.process;
import static junit.framework.TestCase.assertEquals;

public class EndToEnd {

    private final Consumer<String> dummyLog = msg -> {
    };

    @Test
    public void emptySet() {
        String input = "{}";
        assertEquals(input, process(input, dummyLog));
    }

    @Test
    public void formattedFlatSet() {
        String input = "{\n" +
                "    1,\n" +
                "    2,\n" +
                "    3\n" +
                "}";
        assertEquals(input, process(input, dummyLog));
    }

    @Test
    public void oneLineFlat() {
        String input = "{a,b,c}";
        assertEquals(input, process(input, dummyLog));
    }

    @Test
    public void oneLineWithInnerSet() {
        String input = "{a,b,c,{1}}";
        assertEquals(input, process(input, dummyLog));
    }

    @Test
    public void innerSetFormatted() {
        String input = "{\n" +
                "    a,\n" +
                "    {\n" +
                "        1,\n" +
                "        2\n" +
                "    },\n" +
                "    b,\n" +
                "    c\n" +
                "}";
        assertEquals(input, process(input, dummyLog));
    }


    @Test
    public void differentInnerAndOuterFormatting() {
        String input = "{\n" +
                "    a,\n" +
                "    {1,2,3},\n" +
                "    c,\n" +
                "    d\n" +
                "}";
        assertEquals(input, process(input, dummyLog));
    }

    @Test
    public void depthThree() {
        String input = "{\n" +
                "    a,\n" +
                "    b,\n" +
                "    c,\n" +
                "    {\n" +
                "        1,\n" +
                "        2,\n" +
                "        3,\n" +
                "        {\n" +
                "            x,\n" +
                "            y,\n" +
                "            z\n" +
                "        }\n" +
                "    }\n" +
                "}";
        assertEquals(input, process(input, dummyLog));
    }

    @Test
    public void depthFourWithDifferentInnerMostFormatting() {
        String input = "{\n" +
                "    a,\n" +
                "    b,\n" +
                "    c,\n" +
                "    {\n" +
                "        1,\n" +
                "        2,\n" +
                "        3,\n" +
                "        {\n" +
                "            x,\n" +
                "            {aa,ab,ba},\n" +
                "            z\n" +
                "        }\n" +
                "    }\n" +
                "}";
        assertEquals(input, process(input, dummyLog));
    }
}
