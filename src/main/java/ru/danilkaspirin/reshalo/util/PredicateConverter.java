package ru.danilkaspirin.reshalo.util;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Component;
import ru.danilkaspirin.reshalo.domain.Detail;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class PredicateConverter {
    private final Context context;

    public PredicateConverter(Context context) {
        this.context = context;
    }

    // TODO: Жутко косячная и неоптимальная подстановка значений в предикат
    public Predicate<Detail> toPredicate(String string, Map<String, Object> lookup) {
        return detail -> {
            String transformedString = string;
            if (detail.characteristics() != null) {
                for (Map.Entry<String, Object> entry : detail.characteristics().entrySet()) {
                    transformedString = transformedString.replaceAll("[#][{]" + entry.getKey() + "[}]", String.valueOf(entry.getValue()));
                }
            }
            if (lookup != null) {
                for (Map.Entry<String, Object> entry : lookup.entrySet()) {
                    transformedString = transformedString.replaceAll("[$][{]" + entry.getKey() + "[}]", String.valueOf(entry.getValue()));
                }
            }
            return evaluatePredicate(transformedString);
        };
    }

    private boolean evaluatePredicate(String transformedString) {
        return convertToBoolean(transformedString);
    }

    private boolean convertToBoolean(String expression) {
        Value result = context.eval("js", expression);
        return result.asBoolean();
    }
}
