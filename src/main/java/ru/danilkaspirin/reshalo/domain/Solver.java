package ru.danilkaspirin.reshalo.domain;

import java.util.List;

public interface Solver {
    List<Breakage> determineBreakages(Entity entity);
}
