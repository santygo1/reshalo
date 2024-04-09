package ru.danilkaspirin.reshalo.domain;

import java.util.Optional;

public interface Solver {
    Optional<Repair> repair(Detail detail);
}
