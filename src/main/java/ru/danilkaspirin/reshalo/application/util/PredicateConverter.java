package ru.danilkaspirin.reshalo.application.util;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import ru.danilkaspirin.reshalo.domain.Entity;

import java.util.Map;
import java.util.function.Predicate;

public class PredicateConverter {
    private final Context context;
    private final String ctxLanguage;

    public PredicateConverter(Context context, String ctxLanguage) {

        this.context = context;
        this.ctxLanguage = ctxLanguage;
    }

    // TODO: Жутко косячная и неоптимальная подстановка значений в предикат
    public Predicate<Entity> toPredicate(String string, Map<String, Object> lookup) {
        return detail -> {
            String transformedString = string;
            if (detail.characteristics() != null && transformedString.contains("#")) {
                for (Map.Entry<String, Object> entry : detail.characteristics().entrySet()) {
                    transformedString = transformedString.replaceAll("[#][{]" + entry.getKey() + "[}]", String.valueOf(entry.getValue()));
                }
            }
            if (lookup != null && transformedString.contains("$")) {
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
        Value result = context.eval(ctxLanguage, expression);
        return result.asBoolean();
    }
}
