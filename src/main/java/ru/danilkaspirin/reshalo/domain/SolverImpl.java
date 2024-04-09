package ru.danilkaspirin.reshalo.domain;

import java.util.List;
import java.util.Optional;

public class SolverImpl {

    private List<ValidDetailPredicateGroup> validDetailPredicateGroupPredicateGroup;
    private Repair repair;

    public SolverImpl(List<ValidDetailPredicateGroup> validDetailPredicateGroupPredicateGroup, Repair repair){
        this.validDetailPredicateGroupPredicateGroup = validDetailPredicateGroupPredicateGroup;
        this.repair = repair;
    }

    Optional<Repair> repair(Detail detail){
        return Optional.empty();
    }
}
