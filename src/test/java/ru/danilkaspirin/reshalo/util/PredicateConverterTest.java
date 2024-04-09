package ru.danilkaspirin.reshalo.util;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.danilkaspirin.reshalo.domain.Detail;

import java.util.HashMap;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;


class PredicateConverterTest {

    Engine engine = Engine.newBuilder()
            .option("engine.WarnInterpreterOnly", "false")
            .build();
    Context ctx = Context.newBuilder("js").engine(engine).build();

    @Test
    void givenCorrectPredicateWithoutVariable_whenConvert_thenTrue() {
        String stringPredicate = "#{presure} > ${presure_max}";
        Predicate<Detail> predicate = new PredicateConverter(ctx).toPredicate(stringPredicate, new HashMap<>());
        Detail detail = new Detail("id", "name", null);

        boolean actual = predicate.test(detail);
        assertTrue(actual);
    }


    // #{} - internal detail variable, ${} - variable from lookup
    @Test
    void givenCorrectPredicateWithVariable_whenConvert_thenTrue() {
        String stringPredicate = "(${presure_min} < #{presure}) && (#{presure} < ${presure_max})";

        Predicate<Detail> predicate = new PredicateConverter(ctx)
                .toPredicate(stringPredicate, new HashMap<>() {{
                    put("presure_max", 10);
                    put("presure_min", 2);
                }});
        Detail detail = new Detail("id", "name", new HashMap<>() {{
            put("presure",6);
        }});

        boolean actual = predicate.test(detail);
        assertTrue(actual);
    }
}