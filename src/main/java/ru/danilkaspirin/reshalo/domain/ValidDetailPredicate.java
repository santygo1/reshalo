package ru.danilkaspirin.reshalo.domain;

import java.util.function.Predicate;

public record ValidDetailPredicate(Predicate<Detail> predicate, String onSuccessMsg, String onFailureMessage) { }
