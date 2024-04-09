package ru.danilkaspirin.reshalo.domain;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ValidDetailPredicateGroup validDetailPredicateGroup = new ValidDetailPredicateGroup(List.of(
                new ValidDetailPredicate((p) -> (Integer) p.characteristics().get("pressure") > 10, "Давление для детали %s в норме", "Давление для детали %s не в норме"),
                new ValidDetailPredicate((p) -> (Integer) p.characteristics().get("profile") > 10, "Профиль шины для %s в норме", "Профиль шины для детали %s не в норме")
        ));

        Detail detail = new Detail("wheel-1", "Заднее правое колесо", new HashMap<>() {{
            put("pressure", 8);
            put("profile", 11);
        }});

        validDetailPredicateGroup.validDetailsPredicate().forEach(validDetailPredicate -> {
                    if (validDetailPredicate.predicate().test(detail)){
                        System.out.println(String.format(validDetailPredicate.onSuccessMsg(), detail.name().toLowerCase()));
                    }else {
                        System.out.println(String.format(validDetailPredicate.onFailureMessage(), detail.name().toLowerCase()));
                    }
        });
    }
}
