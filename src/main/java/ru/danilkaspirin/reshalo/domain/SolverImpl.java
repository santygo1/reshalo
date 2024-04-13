package ru.danilkaspirin.reshalo.domain;

import java.util.ArrayList;
import java.util.List;

public class SolverImpl implements Solver {

    private final List<EntityPredicateGroup> validEntityPredicateGroup;

    public SolverImpl(List<EntityPredicateGroup> validEntityPredicateGroup) {
        this.validEntityPredicateGroup = validEntityPredicateGroup;
    }

    @Override
    public List<Breakage> determineBreakages(Entity entity) {
        List<Breakage> breakages = new ArrayList<>();

        for (var group : validEntityPredicateGroup){
            boolean isPredicateGroupSuccess = true;
            for (var predicate: group.getValidDetailsPredicate()){
                if (!predicate.predicate().test(entity)){
                    isPredicateGroupSuccess = false;
                    break;
                }
            }
            if(isPredicateGroupSuccess){
                breakages.add(group.getBreakage());
            }
        }

        return breakages;
    }
}
