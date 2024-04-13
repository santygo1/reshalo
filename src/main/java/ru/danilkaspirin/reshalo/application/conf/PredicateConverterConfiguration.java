package ru.danilkaspirin.reshalo.application.conf;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.danilkaspirin.reshalo.application.util.PredicateConverter;

@Configuration
public class PredicateConverterConfiguration {

    @Value("${context}")
    String contextLanguage;

    @Bean
    public Engine engine() {
        return Engine.newBuilder()
                .option("engine.WarnInterpreterOnly", "false")
                .build();
    }

    @Bean
    public Context context(Engine engine) {
        return Context.newBuilder(contextLanguage).engine(engine).build();
    }

    @Bean
    PredicateConverter predicateConverter(Context context) {
        return new PredicateConverter(context, contextLanguage);
    }
}
