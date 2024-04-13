package ru.danilkaspirin.reshalo.domain;

import lombok.Data;

import java.util.List;

@Data
public final class EntityPredicateGroup {

    private final Long id;
    private final List<EntityPredicate> validDetailsPredicate;
    private final Breakage breakage;
    private boolean isFailure = false;

    public EntityPredicateGroup(Long id, List<EntityPredicate> validDetailsPredicate, Breakage breakage) {
        this.id = id;
        this.validDetailsPredicate = validDetailsPredicate;
        this.breakage = breakage;
    }

}
