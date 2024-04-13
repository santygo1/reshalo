package ru.danilkaspirin.reshalo.domain;

import java.util.function.Predicate;

public record EntityPredicate(Long id, Predicate<Entity> predicate) {
}
